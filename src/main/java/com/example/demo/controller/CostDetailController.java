package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.service.CostDetailService;

@Controller
@RequestMapping("/costs")
public class CostDetailController {
	@Autowired
	private CostDetailService costDetailService;
	//列表頁
	@GetMapping
	public String list(Model model) {
		List<CostDetailDTO> costs = costDetailService.getAllCost();
		model.addAttribute("costs", costs);
		return "onion/cost_list";
	}
	
	//顯示新增表單畫面
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("costDetail", new CostDetailDTO());
		return"onion/cost_form";
	}
	
	//新增表單
	@PostMapping
	public String create(@ModelAttribute("costDetail") CostDetailDTO dto) {
		costDetailService.createCost(dto);
		return "redirect:/costs";
	}
	
	//顯示編輯表單
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		CostDetailDTO dto = costDetailService.getById(id);
		model.addAttribute("costDetail", dto);
		return "onion/cost_form";
	}
	
	//編輯更新後處理
	@PostMapping("/update/{id}")
	//@PathVariable Long id 來自網址
	//@ModelAttribute("costdetail") CostDetailDTO dto 來自表單送出的資料
	public String update(@PathVariable Long id, @ModelAttribute("costDetail") CostDetailDTO dto) {
		costDetailService.updateCost(id, dto);
		return "redirect:/costs";
	}

	//刪除表單
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		costDetailService.delete(id);
		return "redirect:/costs";
	}
	

}
