package server.services.implementations;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import server.dtos.AuthResponseDTO;
import server.security.JwtProvider;
import server.services.IAuthService;

@Service
public class AuthService implements IAuthService {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtProvider jwtProvider;

	public AuthService(AuthenticationManager authenticationManager, JwtProvider jwtProvider, UserDetailsService userDetailsService) {
		
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.userDetailsService = userDetailsService;
	}

	public AuthResponseDTO login(String username, String password) {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);			

			String token = jwtProvider.createToken(userDetails.getUsername(), userDetails.getAuthorities());			
			
			AuthResponseDTO userAuth = new AuthResponseDTO(token, userDetails.getUsername(), userDetails.getAuthorities());

			return userAuth;
		}		

		catch (AuthenticationException e) {

			throw new RuntimeException("Incorrect username or password", e);
		}
	}
}
