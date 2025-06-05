package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.entity.SourceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private Integer id;
	private String customerName;
	private String customerPhone;
	private LocalDateTime orderTime;
	private BigDecimal totalAmount;
	private SourceType sourceType;
	private String remark;
	private List<OrderItemDTO> items;

}
