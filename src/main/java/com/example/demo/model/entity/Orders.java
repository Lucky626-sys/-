//訂單主檔，包含日期、總價等資訊
package com.example.demo.model.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private Integer totalAmount;
	
	//這是Order和OrderItem的一對多關係
	//@OneToMany代表一張訂單有多筆訂單明細
	//mappedBy = "order" ->表示關聯是由 OrderItem 裡的 order 欄位來維護的
	//cascade = CascadeType.ALL	-> 儲存、更新、刪除一筆訂單時，所有 OrderItem 也會一起被處理（連帶操作）
	//orphanRemoval = true -> 如果從List<OrderItem> 中刪除一筆item，那筆item會自動從資料庫刪除
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;
	
}
