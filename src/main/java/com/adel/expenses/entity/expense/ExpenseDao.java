package com.adel.expenses.entity.expense;

import com.adel.expenses.service.expensedto.SummaryResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseDao extends JpaRepository<Expense, Long> {
    List<Expense> findAllByDateBetween(LocalDate a, LocalDate b);

    @Query(value = "SELECT  sum(e.amount) FROM Expense e WHERE e.type='INCOME' ")
    Long sumAmount();

    @Query(value = "SELECT new com.adel.expenses.service.expensedto.SummaryResponseDto(e.type, sum(e.amount), count(*)) FROM Expense e group by e.type")
    List<SummaryResponseDto> summaryAmount();

}