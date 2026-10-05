package com.example.HisabAIEntity.entity.setting;


import com.example.HisabAIEntity.entity.common.TenantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "business_settings")
public class BusinessSettings extends TenantEntity {

    @Column(name = "currency_code", nullable = false)
    private String currencyCode = "BDT";

    @Column(name = "default_language", nullable = false)
    private String defaultLanguage = "bn";

    // Gap fix: BR-06 referenced this flag but it didn't exist on the table.
    @Column(name = "allow_negative_stock", nullable = false)
    private boolean allowNegativeStock = false;

    @Column(name = "invoice_prefix")
    private String invoicePrefix;

    @Column(name = "low_stock_threshold_default")
    private Integer lowStockThresholdDefault = 10;
}

