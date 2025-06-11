
package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DailySalesMapper;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.repository.DailySalesRepository;
import com.example.demo.service.MonthlySalesService;
@Service
public class MonthlySalesServiceImpl implements MonthlySalesService{

	@Autowired
	private DailySalesRepository dailySalesRepository;
	
	@Autowired
	private DailySalesMapper mapper;
	
	//顯示「這個月總共賺多少錢」
	@Override
	public BigDecimal findTotalRevenueByYearAndMonth(int year, int month) {
		return dailySalesRepository.findMonthlyTotalRevenueByYearAndMonth(year, month);
				
		
	}
	
	//顯示「每天總共賺多少錢」
	@Override
	public List<DailySalesDTO> findTotalRevenueByDate(int year, int month) {
		return dailySalesRepository.findDailyTotalRevenueByYearAndMonth(year, month);
	}
}