package com.example.demo.service;

import java.util.List;

import com.example.demo.model.dto.OrderDTO;

public interface SearchOrderService {

	public List<OrderDTO> getDetails();
	public OrderDTO getById(Integer id);
	public List<OrderDTO> getByCustomerName(String customerName);
	public List<OrderDTO> getByCustomerPhone(String customerPhone);	//同一姓名、電話有可能多筆資料
}
