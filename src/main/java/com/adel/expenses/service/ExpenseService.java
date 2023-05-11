package com.adel.expenses.service;


import com.adel.expenses.expentity.Expense;
import com.adel.expenses.expentity.ExpenseDao;
import com.adel.expenses.service.expensedto.ExpRequestDto;
import com.adel.expenses.service.expensedto.ExpenseDto;
import com.adel.expenses.service.expensedto.SummaryResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Log4j2
public class ExpenseService {
    @Autowired
    private ExpenseDao expenseDao;

    public ExpenseDto save(ExpRequestDto expRequestDto) {
        LocalDate date = LocalDate.now();
        Expense save = expenseDao.save(Expense.builder()
                .name(expRequestDto.getName())
                .note(expRequestDto.getNote())
                .date(date)
                .amount(expRequestDto.getAmount())
                .type(expRequestDto.getType())
                .build());
        return save.dto();
    }

    public List<ExpenseDto> getAll() {
        List<Expense> all = expenseDao.findAll();
        return Expense.dtos(all);
    }

    public Expense getOne(Long id) {
        return expenseDao.findById(id).get();
    }

    public void deleteOne(Long id) {
        expenseDao.deleteById(id);

    }

    public List<ExpenseDto> getAllDate(String fromDatestr, String toDatestr) {
        LocalDate fromDate = LocalDate.parse(fromDatestr);
        LocalDate toDate = LocalDate.parse(toDatestr);
        List<Expense> all = expenseDao.findAllByDateBetween(fromDate, toDate);
        return Expense.dtos(all);
    }

    public List<SummaryResponseDto> summaryAmount() {
        return expenseDao.summaryAmount();
    }

    public Long sumAmount() {
        return expenseDao.sumAmount();
    }


}



