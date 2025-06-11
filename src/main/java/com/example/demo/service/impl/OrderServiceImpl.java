package com.example.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.PricingService;
import com.example.demo.service.PrintService;
import com.example.demo.service.ProductService;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private PrintService printService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private OrderMapper mapper;

	
	@Override
	public List<OrderDTO> getAllOrders() {
		return orderRepository.findAllByOrderByOrderTimeDesc()
				.stream()
				.map(mapper::toDTO)
				.toList();
	}
	
	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) {
		//計算總價
		BigDecimal totalAmount = pricingService.calculateTotal(orderDTO.getItems());
		orderDTO.setTotalAmount(totalAmount);
		
		//設定訂單時間
		if(orderDTO.getOrderTime() == null) {
			orderDTO.setOrderTime(LocalDateTime.now());
		}
		
		Order order = mapper.toEntity(orderDTO);
		//為了維護雙向關聯的一致姓
		//設定雙向關聯的「反向」關係,確保每個 OrderItem 的 order 欄位正確指向它所屬的 Order 物件
		if(order.getItems() != null ) {
			order.getItems().forEach(item -> item.setOrder(order));
		}
		Order saved = orderRepository.save(order);
		//手動將PersisenBag轉成List(搭配ModelMapper的全域處理)
		saved.setItems(new ArrayList<>(saved.getItems()));
		//列印訂單
		printService.printOrder(saved);
		return mapper.toDTO(saved);
	}

	

	@Override
	public OrderDTO getOrderById(Integer orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NoResourceFoundException("找不到此訂單: orderId" + orderId));
		return mapper.toDTO(order);
	}

	@Override
	public void deleteOrderById(Integer orderId) {
		orderRepository.deleteById(orderId);
	}
}
