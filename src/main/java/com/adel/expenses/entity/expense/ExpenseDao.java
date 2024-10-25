package com.adel.expenses.entity.expense;

import com.adel.expenses.service.expensedto.SummaryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT e FROM Expense e " +
            "WHERE e.user.id = :id " +
            "and e.note like :searchTerm")
    Page<Expense> findAllByUserAndSearchTerm(@Param("id") long id, @Param("searchTerm") String searchTerm, Pageable pageable);
//    Page<Expense> findAllByUser_IdAndNoteContaining(@Param("id") long id,@Param("searchTerm") String searchTerm, Pageable pageable);

    @Query("SELECT e FROM Expense e " +
            "WHERE e.note like :searchTerm")
    Page<Expense> findAllBySearchTerm(@Param("searchTerm") String searchTerm, Pageable pageable);


}