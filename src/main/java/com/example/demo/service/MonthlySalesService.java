package com.example.demo.service;


import java.math.BigDecimal;
import java.util.List;


import com.example.demo.model.dto.DailySalesDTO;

public interface MonthlySalesService {
	//顯示「這個月總共賺多少錢」
	BigDecimal findTotalRevenueByYearAndMonth(int year, int month);

	//顯示「每天總共賺多少錢」
	List<DailySalesDTO> findTotalRevenueByDate(int year, int month);
}
