package com.example.HisabAIEntity.entity.expense;

import com.example.HisabAIEntity.entity.common.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "expense_categories")
public class ExpenseCategory extends SoftDeletableEntity {

    @Column(nullable = false)
    private String name;
}

