package com.adel.expenses.security.jwtutils;

import com.adel.expenses.entity.user.User;
import com.adel.expenses.entity.user.UserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {

    @Autowired
    private UserDao userDao;

    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 10 * 60 * 60;
    @Value("${secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = userDao.findByEmail(userDetails.getUsername());
        claims.put("userId", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("role", new ArrayList<>(userDetails.getAuthorities()).get(0).getAuthority());
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
    public boolean validateJwtToken(Claims claims) {
        if(claims == null) return false;
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return !isTokenExpired;
    }
}