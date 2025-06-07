package com.example.demo.service;


import java.math.BigDecimal;
import java.util.List;


import com.example.demo.model.dto.DailySalesDTO;

public interface MonthlySalesService {
	List<DailySalesDTO> findByYearAndMonth(int year, int month);
	BigDecimal findTotalRevenueByYearAndMonth(int year, int month);

}
