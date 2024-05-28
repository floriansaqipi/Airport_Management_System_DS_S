package com.internationalairport.airportmanagementsystem.security;

import com.internationalairport.airportmanagementsystem.daos.AbilityRepository;
import com.internationalairport.airportmanagementsystem.daos.EmployeeRepository;
import com.internationalairport.airportmanagementsystem.daos.PassengerRepository;
import com.internationalairport.airportmanagementsystem.daos.UserEntityRepository;
import com.internationalairport.airportmanagementsystem.entities.Ability;
import com.internationalairport.airportmanagementsystem.entities.Employee;
import com.internationalairport.airportmanagementsystem.entities.Passenger;
import com.internationalairport.airportmanagementsystem.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private UserEntityRepository userEntityRepository;
    private AbilityRepository abilityRepository;

    private EmployeeRepository employeeRepository;
    private PassengerRepository passengerRepository;

    @Autowired
    public CustomAuthorizationManager(UserEntityRepository userEntityRepository,
                                      AbilityRepository abilityRepository,
                                      EmployeeRepository employeeRepository,
                                      PassengerRepository passengerRepository) {
        this.userEntityRepository = userEntityRepository;
        this.abilityRepository = abilityRepository;
        this.employeeRepository = employeeRepository;
        this.passengerRepository = passengerRepository;
    }


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
        if (!auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }

        String username = auth.getName();
        UserEntity userEntity = userEntityRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        List<Ability> userAbilities = abilityRepository.findByRoleId(userEntity.getRole().getRoleId());
        userEntity.getRole().setAbilities(userAbilities);

        HttpServletRequest request = requestAuthorizationContext.getRequest();

        return new AuthorizationDecision(isAuthorized(userEntity, request));
    }

    private boolean isAuthorized(UserEntity userEntity, HttpServletRequest request){

        List<Ability> abilities = userEntity.getRole().getAbilities();

        String requestUri = request.getRequestURI();
        String method = request.getMethod();

        for (Ability ability : abilities) {
            if(requestUri.contains(ability.getEntity()) &&
                    (ability.getVerb() == null || ability.getVerb().equals(method)) &&
                    ability.getField() == null
            ) {
                return false;
            }
        }
        return true;
    }




}
