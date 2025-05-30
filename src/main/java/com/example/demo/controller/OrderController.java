package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	// 顯示點餐頁面
	@GetMapping("/new")
	public String showOrderForm(Model model) {
		List<ProductDTO> products = productService.findAll();
		model.addAttribute("producs", products);
		model.addAttribute("orderDTO", new OrderDTO());//綁定表單資料
		return "order/new_order";
	}
	
	// 處理送出訂單
	//orderDTO前端送來的訂單資料(送出前)
	//savedOrder後端儲存後回傳的丁單資料(送出後)
	@PostMapping("/new")
	public String submitOrder(OrderDTO orderDTO, Model model) {
		OrderDTO savedOrder = orderService.createOrder(orderDTO);
		model.addAttribute("order", savedOrder);
		return "order/orde_success";//訂單成功頁面
	}
	
	// 查詢所有訂單
	@GetMapping
	public String listOrders(Model model) {
		List<OrderDTO> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "order/order_list";//歷史訂單頁面
	}
}
