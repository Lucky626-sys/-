package com.example.demo.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
	 private Integer id;
	 private Long productId;
	 private String productName;
	 private Integer quantity;
	 private BigDecimal unitPrice;
	 private BigDecimal subtotal;
}
