package com.adel.expenses.security.jwtutils;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Role implements GrantedAuthority {

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
