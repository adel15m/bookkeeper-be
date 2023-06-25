package com.adel.expenses.security.jwtutils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestModel {

    private String username;
    private String password;

}