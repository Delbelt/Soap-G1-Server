package server.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//INITIAL COMMIT: PERSISTENCIA DE PRUEBA - SE BORRARA EN PROXIMAS REVISIONES.

import server.entities.User;

public interface IUserService {
	
	public User findById(int id);

	public User findByUserName(String userName);

	public List<User> getAll();

	public boolean insertOrUpdate(User user);

	public boolean remove(int id);
	
	public Map<String, List<String>> cargarUsuariosDesdeCSV(FileReader fileReader) throws IOException;
}
