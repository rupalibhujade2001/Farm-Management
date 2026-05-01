package com.farmmanagement.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class CropRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_account_id")
    private FarmAccount farmAccount;

    @Column(nullable = false)
    private String cropName;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal expense;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal revenue;

    @Column(nullable = false)
    private LocalDate recordDate;

    public CropRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FarmAccount getFarmAccount() {
        return farmAccount;
    }

    public void setFarmAccount(FarmAccount farmAccount) {
        this.farmAccount = farmAccount;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }
}
