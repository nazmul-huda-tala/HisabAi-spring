package com.example.HisabAIEntity.controller.product;

import com.example.HisabAIEntity.dto.common.ApiResponse;
import com.example.HisabAIEntity.dto.product.BrandRequest;
import com.example.HisabAIEntity.dto.product.BrandResponse;
import com.example.HisabAIEntity.service.product.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<BrandResponse>> list() {
        return ApiResponse.ok(service.list());
    }

    @GetMapping("/{id}")
    public ApiResponse<BrandResponse> get(@PathVariable Long id) {
        return ApiResponse.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BrandResponse>> create(@Valid @RequestBody BrandRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(service.create(request)));
    }

    @PutMapping("/{id}")
    public ApiResponse<BrandResponse> update(@PathVariable Long id, @Valid @RequestBody BrandRequest request) {
        return ApiResponse.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.ok(null, "Brand deleted.");
    }
}
