package com.example.demo.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.entity.DailySales;
//Long 是這個實體的主鍵（id）的資料型態

import jakarta.transaction.Transactional;
public interface DailySalesRepository extends JpaRepository<DailySales, Long> {
	List<DailySales> findByDate(LocalDate date);
    List<DailySales> findByDateBetween(LocalDate start, LocalDate end);
    
    @Modifying
    @Transactional
    @Query(value = 
    "INSERT INTO daily_sales (date, product_id, product_name, quantity, revenue, cost, source_type, created_at, updated_at) " +
    "SELECT DATE(o.order_time) as date, p.product_id, p.product_name, SUM(oi.quantity) as quantity, SUM(oi.unit_price * oi.quantity) as revenue, SUM(p.cost * oi.quantity) as cost, o.source_type, NOW(), NOW() " +
    "FROM orders o " +
    //訂單與明細的關聯
    "JOIN order_items oi ON o.orders_id = oi.orders_id " +
    //明細與商品的關聯
    "JOIN product p ON oi.product_id = p.product_id " +
    //依「日期、商品ID、商品名稱、來源」分組
    "WHERE o.order_time BETWEEN :start AND :end " +
    "GROUP BY DATE(o.order_time), p.product_id, p.product_name, o.source_type",
    nativeQuery = true)
    void summarizeTodaySales(@Param("start") LocalDate start, @Param("end")LocalDate end);
   
    @Query("select SUM(d.revenue) from DailySales d where year(d.date) = :year and month(d.date) = :month")
    BigDecimal findMonthlyTotalRevenueByYearAndMonth(@Param("year") int year, @Param("month") int month);
    
    @Query(value = "SELECT d.date, SUM(d.revenue) " +
            "FROM daily_sales d " +
            "WHERE YEAR(d.date) = :year AND MONTH(d.date) = :month " +
            "GROUP BY d.date ORDER BY d.date", nativeQuery = true)
    List<DailySalesDTO> findDailyTotalRevenueByYearAndMonth(@Param("year") int year, @Param("month") int month);

} 