package com.adel.expenses.entity.expense;

import com.adel.expenses.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "t_expense")
@Builder
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = true)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "note", nullable = false)
    private String note;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseType type;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
