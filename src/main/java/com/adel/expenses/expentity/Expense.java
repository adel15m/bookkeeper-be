package com.adel.expenses.expentity;

import com.adel.expenses.service.expensedto.ExpenseDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Aexpense")
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
    private LocalDate date;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ExpenseType type;



}
