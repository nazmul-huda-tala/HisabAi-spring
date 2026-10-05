package com.example.HisabAIEntity.repository.employee;

import com.example.HisabAIEntity.entity.employee.EmployeeShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeShiftRepository extends JpaRepository<EmployeeShift, Long> {

    List<EmployeeShift> findAllByBusinessId(Long businessId);
    Optional<EmployeeShift> findByIdAndBusinessId(Long id, Long businessId);
}
