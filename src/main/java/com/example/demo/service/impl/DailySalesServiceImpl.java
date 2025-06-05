package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.demo.mapper.CostDetailMapper;
import com.example.demo.mapper.DailySalesMapper;

import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.repository.CostDetailRepository;
import com.example.demo.repository.DailySalesRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.DailySalesService;
@Service
public class DailySalesServiceImpl implements DailySalesService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CostDetailRepository costDetailRepository;
	
	@Autowired
	private DailySalesRepository dailySalesRepository;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private CostDetailMapper costDetailMapper;
	
	@Autowired
	private DailySalesMapper dailySalesMapper;
	
	@Override
	public List<OrderDTO> findAllOrders() {
		return orderRepository.findAll()
				.stream()
				.map(orderMapper::toDTO)
				.toList();
	}

	@Override
	public List<CostDetailDTO> findCostDetailByDate(LocalDate date) {
		return costDetailRepository.findCostDetailByDate(date)
				.stream()
				.map(costDetailMapper::toDTO)
				.toList();
	}

	@Override
	public List<DailySalesDTO> findDailySalesByDate(LocalDate date) {
		return dailySalesRepository.findByDate(date)
				.stream()
				.map(dailySalesMapper::toDTO)
				.toList();
	}

	@Override
	public List<DailySalesDTO> findDailySalesByDateRange(LocalDate start, LocalDate end) {
		return dailySalesRepository.findByDateBetween(start, end)
				.stream()
				.map(dailySalesMapper::toDTO)
				.toList();
	}

	//彙總今日訂單的方法
	public void summarizeTodaySales(LocalDate today) {
		dailySalesRepository.summarizeTodaySales(today, today.plusDays(1));
		
	}
}
