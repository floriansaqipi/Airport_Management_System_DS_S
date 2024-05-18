package com.internationalairport.airportmanagementsystem.security;

import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private UserEntityRepository userEntityRepository;

    @Autowired
    public CustomAuthorizationManager(UserEntityRepository userEntityRepository){
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        // Your custom authorization logic here
        // Example: Allow if the user has a specific authority
//        Authentication auth = authentication.get();
//        System.out.println("hello");
//        boolean hasAuthority = auth.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_USER"));
//        return new AuthorizationDecision(hasAuthority);

        Authentication auth = authentication.get();
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        String requestUri = request.getRequestURI();
        String method = request.getMethod();

        return new AuthorizationDecision(true);
    }
}
