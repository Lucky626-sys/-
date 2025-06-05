package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.OrderDTO;
import com.example.demo.service.SearchOrderService;

@Controller
@RequestMapping("/orders")
public class SearchOrderController {

	@Autowired
	private SearchOrderService searchOrderService;
	
	
	@GetMapping("/search")
	public String searchByCustomer(
			@RequestParam(required = false) String orderId,
			@RequestParam(required = false) String customerName,
			@RequestParam(required = false) String customerPhone,
			Model model) {
		List<OrderDTO> orders;
		if(orderId != null && !orderId.isEmpty()) {
			try {
				Integer id = Integer.parseInt(orderId);
				OrderDTO order = searchOrderService.getById(id);
				//因為Thymeleaf 頁面是用 th:each="order : ${orders}" 來跑訂單清單，
				//所以即使只查到一筆，也要包在 List 裡，這樣頁面才會正常顯示。
				orders = order != null? List.of(order) : List.of();
			}catch(NumberFormatException e) {
				// 如果orderId不是數字，回傳空清單
				//這樣即使輸入錯誤的 orderId（例如打字母），也不會報錯，頁面會顯示查無資料
				model.addAttribute("errorMessage", "請輸入正確的訂單編號");
	            orders = List.of();
			}
		}else if(customerName != null && !customerName.isEmpty()) {
			orders = searchOrderService.getByCustomerName(customerName);
		}else if(customerPhone != null && !customerPhone.isEmpty()) {
			orders = searchOrderService.getByCustomerPhone(customerPhone);
		}else {
			orders = searchOrderService.getDetails();
		}
		model.addAttribute("orders", orders);
		return "onion/search_order";
		
	}
}
