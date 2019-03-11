package com.example.demo.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class SpringSecurityAuditorAware implements AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {

        Optional<Object> principal = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal);



        return principal.filter(UserDetails.class::isInstance)
                .map(UserDetails.class::cast)
                .map(UserDetails::getUsername)
                .or(() -> principal
                        .filter(String.class::isInstance)
                        .map(String.class::cast)
                );
    }
}