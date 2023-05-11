package com.adel.expenses.service.expensedto;

import com.adel.expenses.expentity.ExpenseType;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDto {

    private Long id;
    private String name;
    private String note;
    private LocalDate date;
    private Double amount;
    private ExpenseType type;

}
