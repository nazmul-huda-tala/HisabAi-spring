package com.example.HisabAIEntity.service.supplier;

import com.example.HisabAIEntity.dto.supplier.SupplierPayableResponse;
import com.example.HisabAIEntity.dto.supplier.SupplierRequest;
import com.example.HisabAIEntity.dto.supplier.SupplierResponse;
import com.example.HisabAIEntity.entity.supplier.Supplier;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.supplier.SupplierPayableRepository;
import com.example.HisabAIEntity.repository.supplier.SupplierRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository repository;
    private final SupplierPayableRepository supplierPayableRepository;

    public SupplierService(SupplierRepository repository, SupplierPayableRepository supplierPayableRepository) {
        this.repository = repository;
        this.supplierPayableRepository = supplierPayableRepository;
    }

    @Transactional(readOnly = true)
    public List<SupplierResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public SupplierResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional(readOnly = true)
    public List<SupplierPayableResponse> listPayables(Long supplierId) {
        findOwned(supplierId);
        Long businessId = SecurityUtils.currentBusinessId();
        return supplierPayableRepository.findAllBySupplierIdAndBusinessIdOrderByCreatedAtDesc(supplierId, businessId)
                .stream()
                .map(p -> new SupplierPayableResponse(
                        p.getId(), p.getSupplierId(), p.getPurchaseId(),
                        p.getOriginalAmount(), p.getPayableAmount(), p.isSettled(), p.getCreatedAt()))
                .toList();
    }

    @Transactional
    public SupplierResponse create(SupplierRequest request) {
        Supplier supplier = new Supplier();
        supplier.setBusinessId(SecurityUtils.currentBusinessId());
        apply(supplier, request);
        return toResponse(repository.save(supplier));
    }

    @Transactional
    public SupplierResponse update(Long id, SupplierRequest request) {
        Supplier supplier = findOwned(id);
        apply(supplier, request);
        return toResponse(repository.save(supplier));
    }

    @Transactional
    public void delete(Long id) {
        Supplier supplier = findOwned(id);
        supplier.setDeleted(true);
        repository.save(supplier);
    }

    private void apply(Supplier supplier, SupplierRequest request) {
        supplier.setName(request.name());
        supplier.setPhone(request.phone());
        supplier.setEmail(request.email());
        supplier.setAddress(request.address());
        supplier.setPaymentTerms(request.paymentTerms());
    }

    private Supplier findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(s -> !s.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", id));
    }

    private SupplierResponse toResponse(Supplier s) {
        BigDecimal outstanding = supplierPayableRepository.sumOutstandingBySupplier(s.getId(), s.getBusinessId());
        return new SupplierResponse(
                s.getId(), s.getName(), s.getPhone(), s.getEmail(), s.getAddress(), s.getPaymentTerms(), outstanding
        );
    }
}
