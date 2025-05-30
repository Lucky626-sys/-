//查詢訂單主檔
package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
	List<Order> findByDate(LocalDate date);
}
