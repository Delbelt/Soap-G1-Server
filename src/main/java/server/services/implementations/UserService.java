package server.services.implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import server.entities.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;
import server.entities.User;
import server.repositories.IUserRepository;
import server.services.IStoreService;
import server.services.IUserService;

@Slf4j
@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repository;

	private IStoreService storeService;
	
	@Autowired
    public UserService(IStoreService storeService) {
        this.storeService = storeService;
    }
	
	@Override
	@Transactional(readOnly = true)
	public User findById(int id) {
		
		var response = repository.findById(id).orElse(null);
		
		log.info("[UserService][findById]: " + response);
		
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public User findByUserName(String userName) {
		
		return repository.findByUserName(userName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getAll() {

		return repository.findAll();
	}

	@Override
	@Transactional
	public boolean insertOrUpdate(User user) {

		// TODO enhance handler response and exception

		return repository.save(user) != null ? true : false;
	}

	@Override
	@Transactional
	public boolean remove(int id) {

		boolean isDeleted = false;

		try {
			repository.deleteById(id);
			isDeleted = true;
		}

		catch (Exception e) {
			// TODO: handle exception
		}

		return isDeleted;
	}
	
	@Override
    public Map<String, List<String>> cargarUsuariosDesdeCSV(FileReader fileReader) throws java.io.IOException {
        List<User> usuarios = new ArrayList<>();
        List<String> errores = new ArrayList<>();
        List<String> usuariosCargados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            int lineNumber = 0; // Contador de líneas
            
            while ((line = reader.readLine()) != null) {
                lineNumber++; // Incrementar el número de línea en cada iteración
                String[] datos = line.split(";");
                
                if (datos.length < 5) {
                    errores.add("Línea " + lineNumber + ": Campos incompletos.");
                    continue;
                }
                
                String usuario = datos[0].trim();
                String contraseña = datos[1].trim();
                String nombre = datos[2].trim();
                String apellido = datos[3].trim();
                String codigoTienda = datos[4].trim();

                // Validación de existencia de usuario
                if (repository.findByUserName(usuario) != null) {
                    errores.add("Línea " + lineNumber + ": El usuario ya existe: " + usuario);
                    continue;
                }
                
                // Validación de código de tienda y estado
                Store tienda;
                try {
                    tienda = (Store) storeService.getStoreByCode(codigoTienda);
                    if (!(tienda).isActive()) {
                        errores.add("Línea " + lineNumber + ": La tienda está deshabilitada: " + codigoTienda);
                        continue;
                    }
                } catch (NoSuchElementException e) {
                    errores.add("Línea " + lineNumber + ": Código de tienda no válido: " + codigoTienda);
                    continue;
                }
                
                // Validación de campos vacíos
                if (usuario.isEmpty() || contraseña.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || codigoTienda.isEmpty()) {
                    errores.add("Línea " + lineNumber + ": Campos vacíos.");
                    continue;
                }

                // Crear y configurar el usuario
                User user = new User();
                user.setUserName(usuario);
                user.setPassword(contraseña);
                user.setName(nombre);
                user.setSurname(apellido);
                user.setActive(true);
                user.setStore(tienda);
                usuarios.add(user);
            }
        } catch (IOException e) {
            errores.add("Error al procesar el archivo: " + e.getMessage());
        }

        // Guardar los usuarios válidos en la base de datos
        for (User user : usuarios) {
            repository.save(user);
            usuariosCargados.add(user.getUserName());
        }

        Map<String, List<String>> resultado = new HashMap<>();
        resultado.put("errores", errores);
        resultado.put("usuariosCargados", usuariosCargados);
        return resultado;
    }
}
