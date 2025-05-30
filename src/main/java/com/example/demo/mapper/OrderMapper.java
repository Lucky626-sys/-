package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.entity.Order;

@Component
public class OrderMapper {
		@Autowired
		private ModelMapper modelMapper;
		
		public OrderDTO toDTO(Order order) {
			return modelMapper.map(order, OrderDTO.class);
		}
		
		public Order toEntity(OrderDTO orderDTO) {
			return modelMapper.map(orderDTO, Order.class);
		}
}

