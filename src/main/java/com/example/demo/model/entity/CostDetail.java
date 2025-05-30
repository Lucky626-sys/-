package com.example.demo.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class CostDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName; // 店長、電費、紅豆

    @Enumerated(EnumType.STRING)
    private CostCategory category; // 員工薪資、原料進貨、水電瓦斯...

    private Integer amount; // 金額

    private String month; // 2025-05

    private LocalDate date;

    private String remark;
}