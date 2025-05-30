package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.OrderItemDTO;
import com.example.demo.model.entity.OrderItem;

@Component
public class OrderItemMapper {
	@Autowired
	private ModelMapper modelMapper;
	
	public OrderItemDTO toDTO(OrderItem orderItem) {
		return modelMapper.map(orderItem, OrderItemDTO.class);
	}
	public OrderItem toEntity(OrderItemDTO orderItemDTO) {
		return modelMapper.map(orderItemDTO, OrderItem.class);
	}
	
	
}
