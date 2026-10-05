package com.example.HisabAIEntity.service.product;

import com.example.HisabAIEntity.dto.product.CategoryRequest;
import com.example.HisabAIEntity.dto.product.CategoryResponse;
import com.example.HisabAIEntity.entity.product.ProductCategory;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.product.ProductCategoryRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository repository;

    public ProductCategoryService(ProductCategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional
    public CategoryResponse create(CategoryRequest request) {
        ProductCategory category = new ProductCategory();
        category.setBusinessId(SecurityUtils.currentBusinessId());
        apply(category, request);
        return toResponse(repository.save(category));
    }

    @Transactional
    public CategoryResponse update(Long id, CategoryRequest request) {
        ProductCategory category = findOwned(id);
        apply(category, request);
        return toResponse(repository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        ProductCategory category = findOwned(id);
        category.setDeleted(true);
        repository.save(category);
    }

    private void apply(ProductCategory category, CategoryRequest request) {
        category.setName(request.name());
        category.setParentCategoryId(request.parentCategoryId());
    }

    private ProductCategory findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Product category", id));
    }

    private CategoryResponse toResponse(ProductCategory c) {
        return new CategoryResponse(c.getId(), c.getName(), c.getParentCategoryId());
    }
}
