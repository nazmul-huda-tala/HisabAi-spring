package com.example.HisabAIEntity.repository.product;

import com.example.HisabAIEntity.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByBusinessId(Long businessId);
    Optional<Product> findByIdAndBusinessId(Long id, Long businessId);
    List<Product> findAllByBusinessIdAndDeletedFalse(Long businessId);
    Optional<Product> findBySkuAndBusinessId(String sku, Long businessId);
    Optional<Product> findByBarcodeAndBusinessId(String barcode, Long businessId);
}
