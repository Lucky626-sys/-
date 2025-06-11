package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.service.MonthlySalesService;

@Controller
@RequestMapping("/sales/monthly")
public class MonthlySalesController {

	@Autowired
	private MonthlySalesService monthlySalesService;
	
	
	//顯示當月營業額明細
	//顯示當月營業額總額
	@GetMapping
	public String showMonthlySalesDetail(Model model, 
	        @RequestParam(required = false) Integer year, 
	        @RequestParam(required = false) Integer month) {
		System.out.println("進入 showMonthlySalesDetail");
	    LocalDate now = LocalDate.now();
	    int queryYear = (year != null) ? year : now.getYear();
	    //getMonth()回傳文字，getMonthValue()才回傳數字
	    int queryMonth = (month != null) ? month : now.getMonthValue();
	    
	    BigDecimal monthlyTotalRevenue = monthlySalesService.findTotalRevenueByYearAndMonth(queryYear, queryMonth);
	    List<DailySalesDTO> dailyTotalRevenue = monthlySalesService.findTotalRevenueByDate(queryYear, queryMonth);
	    
	    model.addAttribute("queryYear", queryYear);
	    model.addAttribute("queryMonth", queryMonth);
	    model.addAttribute("monthlyTotalRevenue", monthlyTotalRevenue);
	    model.addAttribute("dailyTotalRevenue", dailyTotalRevenue);
	    
	    return "onion/monthly_sales";
	    
	}
}
