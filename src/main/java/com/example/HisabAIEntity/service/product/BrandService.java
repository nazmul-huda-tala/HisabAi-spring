package com.example.HisabAIEntity.service.product;

import com.example.HisabAIEntity.dto.product.BrandRequest;
import com.example.HisabAIEntity.dto.product.BrandResponse;
import com.example.HisabAIEntity.entity.product.Brand;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.product.BrandRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository repository;

    public BrandService(BrandRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public BrandResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional
    public BrandResponse create(BrandRequest request) {
        Brand brand = new Brand();
        brand.setBusinessId(SecurityUtils.currentBusinessId());
        apply(brand, request);
        return toResponse(repository.save(brand));
    }

    @Transactional
    public BrandResponse update(Long id, BrandRequest request) {
        Brand brand = findOwned(id);
        apply(brand, request);
        return toResponse(repository.save(brand));
    }

    @Transactional
    public void delete(Long id) {
        Brand brand = findOwned(id);
        brand.setDeleted(true);
        repository.save(brand);
    }

    private void apply(Brand brand, BrandRequest request) {
        brand.setName(request.name());
        brand.setLogoUrl(request.logoUrl());
        brand.setActive(request.active() == null || request.active());
    }

    private Brand findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(b -> !b.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Brand", id));
    }

    private BrandResponse toResponse(Brand b) {
        return new BrandResponse(b.getId(), b.getName(), b.getLogoUrl(), b.isActive());
    }
}
