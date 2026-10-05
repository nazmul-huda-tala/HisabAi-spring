package com.example.HisabAIEntity.service.expense;

import com.example.HisabAIEntity.dto.expense.ExpenseRequest;
import com.example.HisabAIEntity.dto.expense.ExpenseResponse;
import com.example.HisabAIEntity.entity.enums.ApprovalStatus;
import com.example.HisabAIEntity.entity.expense.Expense;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.expense.ExpenseRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * NOTE: approval workflow isn't wired up yet (the Approval module has repository only, no
 * service/controller so far), so every expense is created as NOT_REQUIRED. Once Approval
 * gets its service, expenses over a configurable threshold should be created as PENDING here
 * and only flip to APPROVED/REJECTED via that service — never by editing this entity directly.
 */
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ExpenseResponse> list() {
        return repository.findAllByBusinessId(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ExpenseResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional
    public ExpenseResponse create(ExpenseRequest request) {
        Expense expense = new Expense();
        expense.setBusinessId(SecurityUtils.currentBusinessId());
        expense.setCreatedBy(SecurityUtils.currentUserId());
        expense.setApprovalStatus(ApprovalStatus.NOT_REQUIRED);
        apply(expense, request);
        return toResponse(repository.save(expense));
    }

    @Transactional
    public ExpenseResponse update(Long id, ExpenseRequest request) {
        Expense expense = findOwned(id);
        apply(expense, request);
        return toResponse(repository.save(expense));
    }

    @Transactional
    public void delete(Long id) {
        // Transactions are never soft-deleted per the architecture notes on SoftDeletableEntity —
        // if a real "cancel expense" flow is needed later it should reverse it, not remove the row.
        Expense expense = findOwned(id);
        repository.delete(expense);
    }

    private void apply(Expense expense, ExpenseRequest request) {
        expense.setBranchId(request.branchId());
        expense.setCategoryId(request.categoryId());
        expense.setAmount(request.amount());
        expense.setExpenseDate(request.expenseDate());
        expense.setNote(request.note());
        expense.setAttachmentPath(request.attachmentPath());
    }

    private Expense findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Expense", id));
    }

    private ExpenseResponse toResponse(Expense e) {
        return new ExpenseResponse(
                e.getId(), e.getBranchId(), e.getCategoryId(), e.getAmount(), e.getExpenseDate(),
                e.getNote(), e.getAttachmentPath(), e.getApprovalStatus().name(), e.getApprovedBy(), e.getCreatedBy()
        );
    }
}
