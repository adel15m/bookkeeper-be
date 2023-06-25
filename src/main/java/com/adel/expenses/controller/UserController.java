package com.adel.expenses.controller;

import com.adel.expenses.security.jwtutils.JwtRequestModel;
import com.adel.expenses.security.jwtutils.JwtResponseModel;
import com.adel.expenses.security.jwtutils.JwtUserDetailsService;
import com.adel.expenses.security.jwtutils.TokenManager;
import com.adel.expenses.service.user.UserService;
import com.adel.expenses.service.user.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegistrationDto registrationDto){
        userService.register(registrationDto);
    }

    @PostMapping("/login")
    public JwtResponseModel login(@RequestBody JwtRequestModel request) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return new JwtResponseModel(jwtToken);
    }
}