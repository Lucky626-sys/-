package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.MonthlySalesService;

@Controller
@RequestMapping("/sales/monthly")
public class MonthlySalesController {

	private MonthlySalesService monthlySalesService;
	//顯示當月營業額明細
	//顯示當月營業額總額
	@GetMapping
	public String showMonthlySalesDetail(Model model, @RequestParam int year, @RequestParam int month) {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		//getMonth()回傳文字，getMonthValue()才回傳數字
		int month = now.getMonthValue();
		var monthlySalesDetail = monthlySalesService.findByYearAndMonth(year, month);
		var totalRevenue = monthlySalesService.findTotalRevenueByYearAndMonth(year, month);
		model.addAttribute("monthlySalesDetail", monthlySalesDetail);
		model.addAttribute("totalRevenue", totalRevenue);
		return "admin/monthly_sales";
		
	}
	
}
