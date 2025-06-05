//自動統計結果
package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.model.entity.SourceType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailySalesDTO {
	private Long id;
	@NotNull(message = "{dailySalesDTO.date.notNull}")
	private LocalDate date;
	private Long productId;	
	
	@NotEmpty(message = "{dailySalesDTO.productName.notEmpty}")
	private String productName;
	
	@Min(value = 0)
	private Long quantity;
	
	@NotNull(message = "{dailySalesDTO.revenue.notNull}")
	@DecimalMin(value = "0.0", inclusive = true, message = "金額不能是負數")
	private BigDecimal revenue;
	
	@NotNull(message = "{dailySalesDTO.cost.notNull}")
	@DecimalMin(value = "0.0", inclusive = true, message = "金額不能是負數")
	private BigDecimal cost;
	
	private BigDecimal profit;
	
	private SourceType sourceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
	private String remark;
}
