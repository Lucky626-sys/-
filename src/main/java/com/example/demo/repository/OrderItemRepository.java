//查詢訂單明細資料
package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	// Spring Data JPA 提供的 自訂 JPQL 查詢語法
	//從 OrderItem 表中找出該日期的所有商品明細
	@Query("SELECT oi FROM OrderItem oi WHERE oi.order.date = :date")
    List<OrderItem> findByOrders_Date(LocalDate date);
	
	//找出某段時間內的所有訂單明細
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.date BETWEEN :start AND :end")
    List<OrderItem> findByOrders_DateBetween(LocalDate start, LocalDate end);
}
