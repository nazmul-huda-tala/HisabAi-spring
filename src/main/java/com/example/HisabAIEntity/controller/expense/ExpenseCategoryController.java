package com.example.HisabAIEntity.controller.expense;

import com.example.HisabAIEntity.dto.common.ApiResponse;
import com.example.HisabAIEntity.dto.expense.ExpenseCategoryRequest;
import com.example.HisabAIEntity.dto.expense.ExpenseCategoryResponse;
import com.example.HisabAIEntity.service.expense.ExpenseCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-categories")
public class ExpenseCategoryController {

    private final ExpenseCategoryService service;

    public ExpenseCategoryController(ExpenseCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ExpenseCategoryResponse>> list() {
        return ApiResponse.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseCategoryResponse>> create(@Valid @RequestBody ExpenseCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(request)));
    }

    @PutMapping("/{id}")
    public ApiResponse<ExpenseCategoryResponse> update(@PathVariable Long id, @Valid @RequestBody ExpenseCategoryRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok(null, "Expense category deleted.");
    }
}
