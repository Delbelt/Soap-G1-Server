package server.services;

import server.dtos.AuthResponseDTO;

public interface IAuthService {
	
	public AuthResponseDTO login(String username, String password);
}
