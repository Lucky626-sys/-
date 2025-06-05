package com.example.demo.mapper;

import java.math.BigDecimal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;

@Component
public class OrderMapper {
		@Autowired
		private ModelMapper modelMapper;
		@Autowired
		private ProductRepository productRepository;
		
		public OrderDTO toDTO(Order order) {
			return modelMapper.map(order, OrderDTO.class);
		}
		
		
		public Order toEntity(OrderDTO orderDTO) {
			Order order = modelMapper.map(orderDTO, Order.class);
			if(order.getItems() != null) {
				order.getItems().forEach(item -> {
					//subtotal計算 
					if(item.getUnitPrice() != null && item.getQuantity() != null) {
						item.setSubtotal(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
					}
					item.setOrder(order);
				});
			}
			return order;
		}

}

