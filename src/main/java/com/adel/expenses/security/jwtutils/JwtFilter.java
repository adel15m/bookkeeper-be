package com.adel.expenses.security.jwtutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        Claims claims = null;
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.substring(7);
            try {
                claims = tokenManager.getClaims(token);
                username = claims.getSubject();
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            System.out.println("Bearer String not found in token");
        }
        if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenManager.validateJwtToken(claims)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, Collections.singletonList(new Role((String) claims.get("role"))));
                authenticationToken.setDetails(claims);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}