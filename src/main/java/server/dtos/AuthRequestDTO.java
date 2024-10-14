package server.dtos;

import lombok.Data;

@Data
public class AuthRequestDTO {
	
	private String username;
    private String password;

}
