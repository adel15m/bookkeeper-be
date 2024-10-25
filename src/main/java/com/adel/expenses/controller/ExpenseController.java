package com.adel.expenses.controller;


import com.adel.expenses.entity.expense.Expense;
import com.adel.expenses.service.ExpenseService;
import com.adel.expenses.service.expensedto.AllDto;
import com.adel.expenses.service.expensedto.ExpRequestDto;
import com.adel.expenses.service.expensedto.ExpenseDto;
import com.adel.expenses.service.expensedto.SummaryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/expenses")
@CrossOrigin("*")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;


    @PostMapping(value = "/save")
    public ExpenseDto saveExpense(@Valid @RequestBody ExpRequestDto requestDto) {
        return expenseService.save(requestDto);
    }


    @GetMapping(value = "/getone/{id}")
    public Expense getOne(@PathVariable(value = "id") Long id) {
        return expenseService.getOne(id);
    }

    @DeleteMapping(value = "/delone/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable(value = "id") Long id) {
        expenseService.deleteOne(id);
    }


    // pagination, searching notes, sorting by date,
    // todo: amount, type, etc.
    @GetMapping(value = "/getall")
    public AllDto getAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm,
            @RequestParam(value = "sortBy", required = false, defaultValue = "date") String sortBy,
            @RequestParam(value = "desc", required = false, defaultValue = "true") Boolean desc
    ) {
        return expenseService.getAll(pageNumber, pageSize, searchTerm, sortBy, desc);
    }

    @GetMapping(value = "/")
    public List<ExpenseDto> getAllDate(@RequestParam(value = "fromDate") String fromDate,
                                       @RequestParam(value = "toDate") String toDate) {
        return expenseService.getAllDate(fromDate, toDate);
    }

    @GetMapping(value = "/summary")
    public List<SummaryResponseDto> summary() {
        return expenseService.summaryAmount();
    }

    @GetMapping(value = "/sum")
    public Long sum() {
        return expenseService.sumAmount();
    }
}
