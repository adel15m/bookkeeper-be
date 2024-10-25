package com.adel.expenses.service.expensedto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllDto {

    private List<ExpenseDto> list;
    private long count;
    private long pages;

}
