package com.example.demo.model.entity;

import java.math.BigDecimal;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cost_detail")
public class CostDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cost_detail_id")
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private CostCategory category; // 員工薪資、原料進貨、水電瓦斯...
    @Column(name = "item_name", length = 100)
    private String itemName; // 店長、電費、紅豆
    private BigDecimal amount; // 金額
    private LocalDate date;
    private String remark;
 
}