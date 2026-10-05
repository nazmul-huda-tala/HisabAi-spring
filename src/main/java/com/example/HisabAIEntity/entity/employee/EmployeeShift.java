package com.example.HisabAIEntity.entity.employee;

import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "employee_shifts")
public class EmployeeShift extends TenantEntity {

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "check_in", nullable = false)
    private Instant checkIn;

    @Column(name = "check_out")
    private Instant checkOut;
}

