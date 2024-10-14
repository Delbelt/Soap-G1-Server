package server.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtProvider jwtProvider;
	private final UserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
		
		this.jwtProvider = jwtProvider;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	
		String token = getJwtFromRequest(request);

		if (token != null && jwtProvider.validateToken(token)) {
	
			String username = jwtProvider.getUsernameFromToken(token);

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);

			if (userDetails != null) {
				
				var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			
			return bearerToken.substring(7);
		}
		
		return null;
	}
}
