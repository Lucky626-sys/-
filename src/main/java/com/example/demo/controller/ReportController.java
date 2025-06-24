package com.example.demo.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.dto.ReportDTO;
import com.example.demo.service.ReportService;

@Controller
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@GetMapping("/report/search")
	public String getReport(@RequestParam(value = "reportDate", required = false) LocalDate reportDate, Model model) {
		if(reportDate == null ) {
			model.addAttribute("error", "請選擇日期");
			model.addAttribute("reports", Collections.emptyList());
			return "onion/report";
		}
		
		List<ReportDTO> reports = reportService.getReportData(reportDate);
		model.addAttribute("reports", reports);
		model.addAttribute("reportDate", reportDate);
		return "onion/report";
	}
}
