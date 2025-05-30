//訂單明細檔，每筆商品項目：蔥油餅、紅豆等
package com.example.demo.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id")
    private Integer id;

    // 關聯到哪張訂單
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @Column(name = "product_id", insertable = false, updatable = false)
    //productId 只用來讀取資料庫的 product_id 欄位值，不會寫入或更新
    private Long productId;
    
    // 商品名稱（例如：紅豆餅）
    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    private BigDecimal subtotal;
 

    //多個 OrderItem 物件可以對應到同一個 Product
    @ManyToOne
    //表示資料表中，OrderItem 這張表會有一個叫做 product_id 的欄位，用來當作外鍵 (foreign key)，指向 Product 這個資料表的主鍵
    @JoinColumn(name = "product_id")
    private Product product;
}