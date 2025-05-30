package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.OrderItemDTO;
import com.example.demo.service.PricingService;
@Service
public class PricingServiceImpl implements PricingService{

	@Override
	public BigDecimal calculateTotal(List<OrderItemDTO> items) {
		if(items == null || items.isEmpty()) {
			return BigDecimal.ZERO;
		}
		//為了初始化
		BigDecimal total = BigDecimal.ZERO;
		for(OrderItemDTO item: items) {
			if(item.getUnitPrice() != null && item.getQuantity() != null) {
				BigDecimal subtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
				total = total.add(subtotal);
			}
		}
		return total;
	}

}
