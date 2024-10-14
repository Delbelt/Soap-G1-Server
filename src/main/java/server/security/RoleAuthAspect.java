package server.security;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import server.security.SecurityConfig.RoleAuth;

@Aspect
@Component
public class RoleAuthAspect {

    @Around("@annotation(roleAuth)")
    public Object checkRole(ProceedingJoinPoint joinPoint, RoleAuth roleAuth) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasRole = false;

        if (authentication != null) {
            hasRole = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> 
                    Arrays.asList(roleAuth.value()).contains(grantedAuthority.getAuthority())
                );
        }

        if (!hasRole) {
        	
            throw new AccessDeniedException("Access is denied");
        }

        return joinPoint.proceed();
    }
}
