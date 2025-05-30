//訂單明細檔，每筆商品項目：蔥油餅、紅豆等
package com.example.demo.model.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 關聯到哪張訂單
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    // 商品名稱（例如：紅豆餅）
    private String productName;

    private Integer quantity;

    private Integer unitPrice;

    private Integer subtotal;
 

    //多個 OrderItem 物件可以對應到同一個 Product
    @ManyToOne
    //表示資料表中，OrderItem 這張表會有一個叫做 product_id 的欄位，用來當作外鍵 (foreign key)，指向 Product 這個資料表的主鍵
    @JoinColumn(name = "product_id")
    private Product product;

    public Product getProduct() {
        return this.product;
    }
}