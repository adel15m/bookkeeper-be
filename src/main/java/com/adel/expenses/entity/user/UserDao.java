package com.adel.expenses.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
