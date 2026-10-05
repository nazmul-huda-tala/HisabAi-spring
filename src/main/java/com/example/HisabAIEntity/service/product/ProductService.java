package com.example.HisabAIEntity.service.product;

import com.example.HisabAIEntity.dto.product.ProductRequest;
import com.example.HisabAIEntity.dto.product.ProductResponse;
import com.example.HisabAIEntity.entity.product.Product;
import com.example.HisabAIEntity.exception.DuplicateResourceException;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.inventory.InventoryTransactionRepository;
import com.example.HisabAIEntity.repository.product.ProductRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final InventoryTransactionRepository inventoryTransactionRepository;

    public ProductService(ProductRepository repository, InventoryTransactionRepository inventoryTransactionRepository) {
        this.repository = repository;
        this.inventoryTransactionRepository = inventoryTransactionRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional
    public ProductResponse create(ProductRequest request) {
        Long businessId = SecurityUtils.currentBusinessId();

        if (request.sku() != null && !request.sku().isBlank()
                && repository.findBySkuAndBusinessId(request.sku(), businessId).isPresent()) {
            throw new DuplicateResourceException("A product with SKU '" + request.sku() + "' already exists.");
        }

        Product product = new Product();
        product.setBusinessId(businessId);
        apply(product, request);
        return toResponse(repository.save(product));
    }

    @Transactional
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findOwned(id);

        if (request.sku() != null && !request.sku().isBlank() && !request.sku().equals(product.getSku())) {
            Optional<Product> clash = repository.findBySkuAndBusinessId(request.sku(), product.getBusinessId());
            if (clash.isPresent() && !clash.get().getId().equals(id)) {
                throw new DuplicateResourceException("A product with SKU '" + request.sku() + "' already exists.");
            }
        }

        apply(product, request);
        return toResponse(repository.save(product));
    }

    @Transactional
    public void delete(Long id) {
        Product product = findOwned(id);
        product.setDeleted(true);
        repository.save(product);
    }

    private void apply(Product product, ProductRequest request) {
        product.setName(request.name());
        product.setSku(request.sku());
        product.setBarcode(request.barcode());
        product.setCategoryId(request.categoryId());
        product.setBrandId(request.brandId());
        product.setUnit(request.unit());
        product.setCostPrice(request.costPrice());
        product.setSellingPrice(request.sellingPrice());
        product.setTaxRate(request.taxRate());
        product.setTaxCategory(request.taxCategory());
        product.setReorderLevel(request.reorderLevel());
        product.setImageUrl(request.imageUrl());
        product.setActive(request.active() == null || request.active());
    }

    private Product findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(p -> !p.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    private ProductResponse toResponse(Product p) {
        Integer stock = inventoryTransactionRepository.sumQuantityByProduct(p.getId(), p.getBusinessId());
        boolean lowStock = p.getReorderLevel() != null && stock != null && stock <= p.getReorderLevel();

        return new ProductResponse(
                p.getId(), p.getName(), p.getSku(), p.getBarcode(), p.getCategoryId(), p.getBrandId(),
                p.getUnit(), p.getCostPrice(), p.getSellingPrice(), p.getTaxRate(), p.getTaxCategory(),
                p.getReorderLevel(), p.getImageUrl(), p.isActive(), stock, lowStock
        );
    }
}
