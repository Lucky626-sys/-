//查詢訂單主檔
package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long>{
	List<Orders> findByDate(LocalDate date);
}
