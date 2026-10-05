package com.example.HisabAIEntity.entity.identity;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class Branch extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "is_main_branch", nullable = false)
    private boolean mainBranch = false;

    private String address;

    private String phone;
}
