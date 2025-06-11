package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.OrderDTO;

public interface OrderService {

	public List<OrderDTO> getAllOrders();
	public OrderDTO createOrder(OrderDTO orderDTO);
	public OrderDTO  getOrderById(Integer orderId);
	public void deleteOrderById(Integer orderId);
}
