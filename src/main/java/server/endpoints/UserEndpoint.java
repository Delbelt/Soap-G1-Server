package server.endpoints;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.services.IUserService;
import server.soap.RouterSoap;
import soap.users.CargarUsuariosRequest;
import soap.users.CargarUsuariosResponse;
import soap.users.Errores;
import soap.users.UsuariosCargados;

@Endpoint
public class UserEndpoint {
	@Autowired
	private IUserService userService;

	@PayloadRoot(namespace = RouterSoap.NAMESPACE_USER, localPart = "CargarUsuariosRequest")
	@ResponsePayload
	public CargarUsuariosResponse cargarUsuarios(@RequestPayload CargarUsuariosRequest request)
			throws java.io.IOException {
		CargarUsuariosResponse response = new CargarUsuariosResponse();

		// Inicializar los objetos `usuariosCargados` y `errores`
		UsuariosCargados usuariosCargados = new UsuariosCargados();
		Errores errores = new Errores();
		response.setUsuariosCargados(usuariosCargados);
		response.setErrores(errores);

		String fileContent = request.getFileContent();
		/*
		 * 
		 * 
		 * // Crear archivo temporal File tempFile = File.createTempFile("usuarios",
		 * ".csv"); try (FileWriter writer = new FileWriter(tempFile)) {
		 * writer.write(fileContent); // Escribir el contenido en el archivo }
		 */
		// Decodifica el contenido Base64
		byte[] decodedBytes = Base64.getDecoder().decode(fileContent);
		String decodedContent = new String(decodedBytes);

		// Crear archivo temporal
		File tempFile = File.createTempFile("usuarios", ".csv");
		try (FileWriter writer = new FileWriter(tempFile)) {
			writer.write(decodedContent); // Escribir el contenido decodificado en el archivo
		}
		try (FileReader fileReader = new FileReader(tempFile)) {
			// Llamada al servicio
			Map<String, List<String>> resultado = userService.cargarUsuariosDesdeCSV(fileReader);

			// Agregar los resultados a la respuesta
			if (resultado.containsKey("usuariosCargados")) {
				List<String> usuarios = resultado.get("usuariosCargados");
				System.out.println("Usuarios cargados: " + usuarios);
				usuariosCargados.getUsuario().addAll(usuarios);
			}

			if (resultado.containsKey("errores")) {
				List<String> erroresList = resultado.get("errores");
				System.out.println("Errores encontrados: " + erroresList);
				errores.getError().addAll(erroresList);
			}
		} finally {
			tempFile.delete(); // Borrar el archivo temporal
		}
		System.out.println("Contenido del archivo recibido: " + fileContent);

		System.out.println("Respuesta final - Usuarios: " + response.getUsuariosCargados().getUsuario());
		System.out.println("Respuesta final - Errores: " + response.getErrores().getError());

		return response;
	}

}
