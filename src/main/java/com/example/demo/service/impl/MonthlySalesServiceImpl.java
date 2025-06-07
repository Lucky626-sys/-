package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	
	@Override
	public List<DailySalesDTO> findByYearAndMonth(int year, int month) {
		return dailySalesRepository.findByYearAndMonth(year, month)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public BigDecimal findTotalRevenueByYearAndMonth(int year, int month) {
		return dailySalesRepository.findTotalRevenueByYearAndMonth(year, month);
				
	}

}
