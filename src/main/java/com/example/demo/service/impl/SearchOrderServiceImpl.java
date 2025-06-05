package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.SearchOrderService;
@Service
public class SearchOrderServiceImpl implements SearchOrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderMapper mapper;
	
	@Override
	public List<OrderDTO> getDetails() {
		return orderRepository.findAll()
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public OrderDTO getById(Integer id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new NoResourceFoundException("找不到此訂單: orderId" + id));
		return mapper.toDTO(order);
	}

	@Override
	public List<OrderDTO> getByCustomerName(String customerName) {
		return orderRepository.findByCustomerName(customerName)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public List<OrderDTO> getByCustomerPhone(String customerPhone) {
		return orderRepository.findByCustomerPhone(customerPhone)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

}
