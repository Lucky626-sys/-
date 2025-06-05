//訂單明細檔，每筆商品項目：蔥油餅、紅豆等
package com.example.demo.model.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id")
    private Integer id;

    // 關聯到哪張訂單
    @ManyToOne
    @JoinColumn(name = "orders_id")
    @JsonBackReference   // 反向引用，序列化時會被忽略，避免無限遞迴
    private Order order;
    
    //多個 OrderItem 物件可以對應到同一個 Product
    @ManyToOne
    //表示資料表中，OrderItem 這張表會有一個叫做 product_id 的欄位，用來當作外鍵 (foreign key)，指向 Product 這個資料表的主鍵
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private BigDecimal subtotal;
 
 
}