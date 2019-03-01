package com.example.demo.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class AccessPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object owner, Object o1) {
        boolean hasPermission = false;

        if (authentication != null && owner != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails user = (UserDetails) authentication.getPrincipal();
                if (user.getUsername().equals(owner.toString())) {
                    hasPermission = true;
                }
            }

        }

        return hasPermission;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false;
    }
}