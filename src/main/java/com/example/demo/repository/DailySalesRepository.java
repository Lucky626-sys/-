package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.DailySales;
//Long 是這個實體的主鍵（id）的資料型態
public interface DailySalesRepository extends JpaRepository<DailySales, Long> {
	List<DailySales> findByDate(LocalDate date);
    List<DailySales> findByDateBetween(LocalDate start, LocalDate end);
}