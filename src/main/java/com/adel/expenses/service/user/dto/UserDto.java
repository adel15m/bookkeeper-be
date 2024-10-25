package com.adel.expenses.service.user.dto;

import com.adel.expenses.entity.user.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;

    public static UserDto dto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
