package com.example.HisabAIEntity.repository.employee;

import com.example.HisabAIEntity.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findAllByBusinessId(Long businessId);
    Optional<Employee> findByIdAndBusinessId(Long id, Long businessId);
    List<Employee> findAllByBusinessIdAndDeletedFalse(Long businessId);
}
