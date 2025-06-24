package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

	private LocalDate date;
	private String productName;
	private BigDecimal unitCost;
	private BigDecimal revenue;
	private BigDecimal totalCost;
	private BigDecimal grossProfit;
}
