package com.adel.expenses.service.expensedto;


import com.adel.expenses.entity.expense.ExpenseType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpRequestDto {

    private String name;
    private String note;
    private Date date;
    private Double amount;
    private ExpenseType type;


}
