package com.example.HisabAIEntity.controller.supplier;

import com.example.HisabAIEntity.dto.common.ApiResponse;
import com.example.HisabAIEntity.dto.supplier.SupplierPayableResponse;
import com.example.HisabAIEntity.dto.supplier.SupplierRequest;
import com.example.HisabAIEntity.dto.supplier.SupplierResponse;
import com.example.HisabAIEntity.service.supplier.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<SupplierResponse>> list() {
        return ApiResponse.ok(service.list());
    }

    @GetMapping("/{id}")
    public ApiResponse<SupplierResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @GetMapping("/{id}/payables")
    public ApiResponse<List<SupplierPayableResponse>> payables(@PathVariable Long id) {
        return ApiResponse.ok(service.listPayables(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierResponse>> create(@Valid @RequestBody SupplierRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(request)));
    }

    @PutMapping("/{id}")
    public ApiResponse<SupplierResponse> update(@PathVariable Long id, @Valid @RequestBody SupplierRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok(null, "Supplier deleted.");
    }
}
