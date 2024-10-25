package com.adel.expenses.service;


import com.adel.expenses.entity.expense.Expense;
import com.adel.expenses.entity.expense.ExpenseDao;
import com.adel.expenses.entity.user.User;
import com.adel.expenses.entity.user.UserDao;
import com.adel.expenses.entity.user.UserRole;
import com.adel.expenses.security.LoggedInUser;
import com.adel.expenses.service.expensedto.AllDto;
import com.adel.expenses.service.expensedto.ExpRequestDto;
import com.adel.expenses.service.expensedto.ExpenseDto;
import com.adel.expenses.service.expensedto.SummaryResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ExpenseService {
    @Autowired
    private ExpenseDao expenseDao;
    @Autowired
    private UserDao userDao;

    public ExpenseDto save(ExpRequestDto expRequestDto) {
        LocalDateTime date = LocalDateTime.now();
        Long userId = LoggedInUser.getUserId();
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) {
            // throw error
            log.error("user does not exist");
            return null;
        }

        User user = optionalUser.get();
        Expense save = expenseDao.save(Expense.builder()
                .name(expRequestDto.getName())
                .note(expRequestDto.getNote())
                .date(date)
                .amount(expRequestDto.getAmount())
                .type(expRequestDto.getType())
                .user(user)
                .build());

        return ExpenseDto.dto(save);
    }

    public List<ExpenseDto> getAll() {

        String userName = LoggedInUser.getUserName();
        Long userId = LoggedInUser.getUserId();
        if(LoggedInUser.hasRole(UserRole.REGULAR)){
            System.out.println("regular user");
        } else if(LoggedInUser.hasRole(UserRole.ADMIN)){
            System.out.println("admin user");
        }

        List<Expense> all = expenseDao.findAll();
        return ExpenseDto.dtos(all);
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
        return ExpenseDto.dtos(all);
    }

    public List<SummaryResponseDto> summaryAmount() {
        return expenseDao.summaryAmount();
    }

    public Long sumAmount() {
        return expenseDao.sumAmount();
    }


    public List<ExpenseDto> getFive() {
        return new ArrayList<>();
    }
}



