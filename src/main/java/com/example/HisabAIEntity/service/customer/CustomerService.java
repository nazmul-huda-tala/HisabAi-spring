package com.example.HisabAIEntity.service.customer;

import com.example.HisabAIEntity.dto.customer.CustomerDueResponse;
import com.example.HisabAIEntity.dto.customer.CustomerRequest;
import com.example.HisabAIEntity.dto.customer.CustomerResponse;
import com.example.HisabAIEntity.entity.customer.Customer;
import com.example.HisabAIEntity.exception.ResourceNotFoundException;
import com.example.HisabAIEntity.repository.customer.CustomerDueRepository;
import com.example.HisabAIEntity.repository.customer.CustomerRepository;
import com.example.HisabAIEntity.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerDueRepository customerDueRepository;

    public CustomerService(CustomerRepository repository, CustomerDueRepository customerDueRepository) {
        this.repository = repository;
        this.customerDueRepository = customerDueRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> list() {
        return repository.findAllByBusinessIdAndDeletedFalse(SecurityUtils.currentBusinessId())
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CustomerResponse get(Long id) {
        return toResponse(findOwned(id));
    }

    @Transactional(readOnly = true)
    public List<CustomerDueResponse> listDues(Long customerId) {
        findOwned(customerId); // 404s if this customer isn't ours
        Long businessId = SecurityUtils.currentBusinessId();
        return customerDueRepository.findAllByCustomerIdAndBusinessIdOrderByCreatedAtDesc(customerId, businessId)
                .stream()
                .map(d -> new CustomerDueResponse(
                        d.getId(), d.getCustomerId(), d.getSaleId(),
                        d.getOriginalAmount(), d.getDueAmount(), d.isSettled(), d.getCreatedAt()))
                .toList();
    }

    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setBusinessId(SecurityUtils.currentBusinessId());
        apply(customer, request);
        return toResponse(repository.save(customer));
    }

    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        Customer customer = findOwned(id);
        apply(customer, request);
        return toResponse(repository.save(customer));
    }

    @Transactional
    public void delete(Long id) {
        Customer customer = findOwned(id);
        customer.setDeleted(true);
        repository.save(customer);
    }

    private void apply(Customer customer, CustomerRequest request) {
        customer.setName(request.name());
        customer.setPhone(request.phone());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        customer.setCreditLimit(request.creditLimit() == null ? BigDecimal.ZERO : request.creditLimit());
    }

    private Customer findOwned(Long id) {
        return repository.findByIdAndBusinessId(id, SecurityUtils.currentBusinessId())
                .filter(c -> !c.isDeleted())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    private CustomerResponse toResponse(Customer c) {
        BigDecimal outstanding = customerDueRepository.sumOutstandingByCustomer(c.getId(), c.getBusinessId());
        return new CustomerResponse(
                c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress(), c.getCreditLimit(), outstanding
        );
    }
}
