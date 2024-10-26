package com.adel.expenses.service.expensedto;

import com.adel.expenses.entity.expense.Expense;
import com.adel.expenses.entity.expense.ExpenseType;
import com.adel.expenses.service.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private LocalDateTime date;
    private Double amount;
    private ExpenseType type;
    private UserDto user;


    public static List<ExpenseDto> dtos(List<Expense> expenses) {
        List<ExpenseDto> list = new ArrayList<>();
        for (Expense expense : expenses) {
            list.add(ExpenseDto.dto(expense));
        }
        return list;
    }

    public static ExpenseDto dto(Expense expense) {
        return ExpenseDto.builder()
                .id(expense.getId())
                .name(expense.getName())
                .note(expense.getNote())
                .date(expense.getDate())
                .amount(expense.getAmount())
                .type(expense.getType())
                .user(UserDto.dto(expense.getUser()))
                .build();
    }
}
