//查詢訂單明細資料
package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{
	// Spring Data JPA 提供的 自訂 JPQL 查詢語法
	//找出某段時間內的所有訂單明細
    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.orderTime BETWEEN :start AND :end")
    List<OrderItem> findByOrderOrderTimeBetween(@Param("start") LocalDateTime start, @Param("end")LocalDateTime end);
    //從 OrderItem 表中找出該日期的所有商品明細
    //預設實作方法，用來寫一些自訂的輔助邏輯（例如組合查詢條件、轉換參數
    default List<OrderItem> findByOrderDate(LocalDate date){
    	LocalDateTime start = date.atStartOfDay();
    	//將日期加一天 -> 取得該日的最後一刻 -> 減去一奈秒
    	LocalDateTime end = date.plusDays(1).atStartOfDay().minusNanos(1);

		return findByOrderOrderTimeBetween(start, end);
    }
  
}
