package com.adel.expenses.service.user;

import com.adel.expenses.entity.user.User;
import com.adel.expenses.entity.user.UserDao;
import com.adel.expenses.entity.user.UserRole;
import com.adel.expenses.service.user.dto.RegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void register(RegistrationDto registrationDto) {
        userDao.save(User
                .builder()
                .email(registrationDto.getUsername())
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .password(hashedPassword(registrationDto.getPassword()))
                .role(UserRole.REGULAR)
                .build());
    }

    private String hashedPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}


