package com.adel.expenses.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/get")
public class GetController {


    public static final long TOKEN_VALIDITY = 10 * 60 * 60;
    @Value("${secret}")
    private String jwtSecret;
    @GetMapping(value = "/test1")
    public String test1() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 234234324);
        claims.put("role", "ADMIN");
        claims.put("company", "company-id-123");

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject("username123")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    @GetMapping(value = "/test2")
    public String test2(@RequestParam(value = "x", defaultValue = "0") int x,
                        @RequestParam(value = "y", defaultValue = "0") int y,
                        @RequestHeader(value = "sec-ch-ua-platform",required = false) String temp) {
        return String.format("%d x %d = %d", x, y, x * y);
    }

    @GetMapping(value = "/test3/{x}/{y}")
    public String test3(@PathVariable(value = "x") int x,
                        @PathVariable(value = "y") int y) {
        return String.format("%d x %d = %d", x, y, x * y);
    }

    @GetMapping(value = "/test4/{x}")
    public String test4(@PathVariable(value = "x") int x,
                        @RequestHeader(value = "y") int y) {
        return String.format("%d x %d = %d", x, y, x * y);
    }


}


