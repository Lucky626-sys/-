package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	//電話是唯一值
	Optional<Customer> findByPhone(String phone);
	//	用完整姓名查詢（可能同名），回傳多筆
	List<Customer> findByName(String name);
	//模糊搜尋，支援關鍵字查詢
	List<Customer> findByPhoneContainingOrNameContaining(String phone, String name);
}
