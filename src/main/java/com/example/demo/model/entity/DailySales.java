//每日營業額
package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="daily_sales")
public class DailySales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_sales_id")
    private Long id;
    private LocalDate date;		 //日期
    
    @Column(name = "product_id")
    private Long productId;		 //品項id
    
    @Column(name = "product_name")
    private String productName; 	 //品項(芋頭餅、蔥油餅)
    private Long quantity;	 	 //商品數量合計
    private BigDecimal revenue;  //銷貨收入
    private BigDecimal cost;	 //商品成本
    
    @Column(name = "source_type")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;//每次新增時間
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;//更新時間
    
    @Column(length = 255)
    private String remark;		 //備註
    
    @Transient	//JPA 的註解，代表這個欄位不會被存到資料庫，也就是「非持久化」欄位
    private BigDecimal profit;   	 //營業額，不存資料庫，自動計算

    public BigDecimal getProfit() {
        if (revenue != null && cost != null) {
            return revenue.subtract(cost);
        }
        return null;
    }
}