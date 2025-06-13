// 手動輸入 營業額
package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.mapper.DailySalesMapper;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.service.DailySalesService;

@Controller
@RequestMapping("/sales/daily")
public class DailySalesController {

    @Autowired
    private DailySalesService dailySalesService;
    
    @Autowired
    private DailySalesMapper mapper;

    //顯示今日營業額
    @GetMapping
    public String showDailySalesForm(Model model) {
    	LocalDate today = LocalDate.now();
    	List<DailySalesDTO> dailysales = dailySalesService.findDailySalesByDate(today);
    	
    	//計算今日總營業額
    	BigDecimal totalRevenue = dailysales.stream().map(DailySalesDTO::getRevenue).reduce(BigDecimal.ZERO, BigDecimal::add);
    	model.addAttribute("date", today);
    	model.addAttribute("dailysales", dailysales);
    	model.addAttribute("totalRevenue", totalRevenue);
    	return "onion/daily_sales";
    }
    
    // 觸發今日彙總（可做成按鈕或自動排程）
    @PostMapping("/summarize")
    public String summarizeTodaySales() {
    	//LocalDate.now(); 寫在 Controller，因為這屬於「請求參數」的決定，和 HTTP 請求上下文有關
    	LocalDate today = LocalDate.now();
    	dailySalesService.summarizeTodaySales(today);
		return "redirect:/sales/daily";
    }
    
    //查詢指定日期營業額
    @PostMapping("/search")
    public String searchDailySales(@RequestParam("date") String dateString, Model model) {
    	if(dateString == null || dateString.isEmpty()) {
    		model.addAttribute("error", "請選擇日期");
    		model.addAttribute("dailsales", Collections.emptyList());
    		return "onion/daily_sales";
    	}
    	
    	//parse 是 Java 8 的 LocalDate 類別裡的一個靜態方法，用來把「字串」轉換成 LocalDate
    	LocalDate date = LocalDate.parse(dateString);
    	List<DailySalesDTO> dailysales = dailySalesService.findDailySalesByDate(date);
    	model.addAttribute("dailysales", dailysales);
    	model.addAttribute("date", date);
    	return "onion/daily_sales";
    }
    
  
    
}
