package com.adel.expenses.entity.user;

import com.adel.expenses.entity.expense.Expense;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_user")
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "first_name", unique = false, nullable = false)
    private String firstName;

    @Column(name = "last_name", unique = false, nullable = false)
    private String lastName;

    @Column(name = "role", unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Expense> expenses;


}
