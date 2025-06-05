package com.example.demo.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.model.entity.CostCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostDetailDTO {
	private Long id;
    private CostCategory category;
    //在mapper裡面寫對應抓取的程式碼
    private String categoryDisplayName; //類型中文名稱
    private String itemName; 
    private BigDecimal amount; 
    private LocalDate date;
    private String dateStr; //字串日期
    private String remark;	
}
