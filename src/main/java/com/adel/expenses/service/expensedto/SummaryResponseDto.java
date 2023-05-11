package com.adel.expenses.service.expensedto;

import com.adel.expenses.expentity.ExpenseType;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SummaryResponseDto {
    private ExpenseType type;
    private Double amount;
    private Long count;


}









