package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.example.demo.model.dto.OrderItemDTO;

public interface PricingService {

	BigDecimal calculateTotal(List<OrderItemDTO> items);
}
