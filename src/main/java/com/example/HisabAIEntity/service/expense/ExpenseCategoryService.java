package com.example.HisabAIEntity.service.expense;

import com.example.HisabAIEntity.dto.expense.ExpenseCategoryRequest;
import com.example.HisabAIEntity.dto.expense.ExpenseCategoryResponse;
import com.example.HisabAIEntity.entity.expense.ExpenseCategory;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.expense.ExpenseCategoryRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository repository;

    public ExpenseCategoryService(ExpenseCategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseCategoryResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(c -> new ExpenseCategoryResponse(c.getId(), c.getName())).toList();
    }

    @Transactional
    public ExpenseCategoryResponse create(ExpenseCategoryRequest request) {
        ExpenseCategory category = new ExpenseCategory();
        category.setBusinessId(SecurityUtils.currentBusinessId());
        category.setName(request.name());
        category = repository.save(category);
        return new ExpenseCategoryResponse(category.getId(), category.getName());
    }

    @Transactional
    public ExpenseCategoryResponse update(Long id, ExpenseCategoryRequest request) {
        ExpenseCategory category = findOwned(id);
        category.setName(request.name());
        category = repository.save(category);
        return new ExpenseCategoryResponse(category.getId(), category.getName());
    }

    @Transactional
    public void delete(Long id) {
        ExpenseCategory category = findOwned(id);
        category.setDeleted(true);
        repository.save(category);
    }

    private ExpenseCategory findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Expense category", id));
    }
}
