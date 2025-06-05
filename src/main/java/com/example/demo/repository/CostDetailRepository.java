package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.entity.CostDetail;

public interface CostDetailRepository extends JpaRepository<CostDetail, Long> {
	List<CostDetail> findCostDetailByDate(LocalDate date);
	//查詢某年某月
	//function('YEAR', c.date)：呼叫資料庫的 YEAR() 函數，取得 c 這筆資料的 date 欄位的年份
	//= :year：這個年份要等於你傳進來的參數 year
	//function('MONTH', c.date)：呼叫資料庫的 MONTH() 函數，取得 c 這筆資料的 date 欄位的月份
	//= :month：這個月份要等於你傳進來的參數 month
	@Query("select c from CostDetail c where function('YEAR', c.date) = :year AND function('MONTH', c.date) = :month")
	List<CostDetail> findByYearAndMonth(@Param("year")int year, @Param("month") int month);
	
}