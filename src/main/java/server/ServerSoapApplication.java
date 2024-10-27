package server;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import server.entities.Catalog;
import server.services.ICatalogService;

@SpringBootApplication
public class ServerSoapApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerSoapApplication.class, args);
		
	}
}
