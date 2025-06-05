package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.SourceType;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ProductService productService;

	// 顯示點餐頁面
	@GetMapping("/new")
	public String showOrderForm(Model model) {
		List<ProductDTO> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("orderDTO", new OrderDTO());// 綁定表單資料
		return "onion/new_order";
	}

	// 處理送出訂單
	// orderDTO前端送來的訂單資料(送出前)
	// savedOrder後端儲存後回傳的訂單資料(送出後)
	@PostMapping("/new")
	public String submitOrder(OrderDTO orderDTO, Model model) {
	    try {
	    	orderDTO.setSourceType(SourceType.AUTO);
	        OrderDTO savedOrder = orderService.createOrder(orderDTO);
	        System.out.println("儲存後訂單: " + savedOrder);
	        model.addAttribute("order", savedOrder);
	        return "onion/order_success";
	    } catch (Exception e) {
	        e.printStackTrace(); // 這行會把錯誤訊息完整印出
	        model.addAttribute("errorMessage", e.getMessage());
	        return "error"; // 你可以自訂 error.html
	    }
	}


	// 查詢所有訂單
	@GetMapping
	public String listOrders(Model model) {
		List<OrderDTO> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		
		//計算總金額
		BigDecimal totalAmount = orders.stream()//把訂單清單（List<OrderDTO>）轉成 Stream，方便用函數式方式處理
				.map(OrderDTO::getTotalAmount)	// 取出每一筆訂單的 totalAmount 欄位
				.filter(Objects::nonNull)		// 過濾掉為 null 的金額（避免加總時出錯）
				.reduce(BigDecimal.ZERO, BigDecimal::add);// 將所有金額累加，初始值為 0
		model.addAttribute("totalAmount", totalAmount);
		return "onion/order_list";// 歷史訂單頁面
	}
	
	//顯示手動補登訂單畫面
	@GetMapping("/manual-new")
	public String showManualOrderForm(Model model) {
		List<ProductDTO> products = productService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("orderDTO", new OrderDTO());
		return"onion/manual_new_order";
	}
	
	//新增手動補登訂單
	@PostMapping("/manual-new")
	public String submitManualOrder(OrderDTO orderDTO, Model model) {
		orderDTO.setSourceType(SourceType.MANUAL);
		OrderDTO saved = orderService.createOrder(orderDTO);
		model.addAttribute("order", saved);
		return "onion/order_success";
	}
}
