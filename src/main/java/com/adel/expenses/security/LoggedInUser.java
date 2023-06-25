package com.adel.expenses.security;

import com.adel.expenses.entity.user.UserRole;
import com.adel.expenses.security.jwtutils.Role;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInUser {


    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public static boolean hasRole(UserRole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(new Role(role.name()));
    }


    public static Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userId = ((DefaultClaims) authentication.getDetails()).get("userId");
        if (userId instanceof Integer) {
            return new Long(((Integer) userId));
        }
        return (Long) ((DefaultClaims) authentication.getDetails()).get("userId");
    }

    public static String getFirstName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) ((DefaultClaims) authentication.getDetails()).get("firstName");
    }


}
