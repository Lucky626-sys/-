//查詢訂單主檔
package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
	List<Order> findByOrderTimeBetween(LocalDateTime start, LocalDateTime end);
	List<Order> findByCustomerName(String customerName);
	List<Order> findByCustomerPhone(String customerNumber);
	//Spring Data JPA 的命名規則
	List<Order> findAllByOrderByOrderTimeDesc();
}
