package com.example.HisabAIEntity.repository.expense;

import com.example.HisabAIEntity.entity.expense.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {

    List<ExpenseCategory> findAllByBusinessId(Long businessId);
    Optional<ExpenseCategory> findByIdAndBusinessId(Long id, Long businessId);
    List<ExpenseCategory> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
