package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.dto.OrderDTO;

public interface DailySalesService {

	List<OrderDTO> findAllOrders();
	List<CostDetailDTO> findCostDetailByDate(LocalDate date);
	List<DailySalesDTO> findDailySalesByDate(LocalDate date);
	//可用在報表查詢多日的
	List<DailySalesDTO> findDailySalesByDateRange(LocalDate start, LocalDate end);
	void summarizeTodaySales(LocalDate today);
}
