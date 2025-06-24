package com.example.demo.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.dto.ReportDTO;
import com.example.demo.service.CostDetailService;
import com.example.demo.service.DailySalesService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReportService;
@Service
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ProductService productService;
	@Autowired
	private CostDetailService costDetailService;
	@Autowired
	private DailySalesService dailySalesService;

	@Override
	public List<ReportDTO> getReportData(LocalDate date) {
		List<ProductDTO> products = productService.findAll();
		List<CostDetailDTO> manualCosts = costDetailService.getCostByDate(date);
		List<DailySalesDTO> dailySales = dailySalesService.findDailySalesByDate(date);
		List<ReportDTO> reports = new ArrayList<>();
		
		for(DailySalesDTO sale : dailySales) {
			ProductDTO product = products.stream()
					.filter(p -> sale.getProductName().equals(p.getName()))
					.findFirst()
					.orElse(null);
			if(product == null) continue;
			
			ReportDTO report = new ReportDTO();
			//從LocalDate轉型成String
			report.setDate(date);
			report.setProductName(product.getName());
			report.setUnitCost(product.getCost());
			report.setRevenue(sale.getRevenue());
			
			BigDecimal productCost = product.getCost() != null? 
					product.getCost().multiply(BigDecimal.valueOf(sale.getQuantity())) : BigDecimal.ZERO;
			report.setTotalCost(productCost);
			report.setGrossProfit(report.getRevenue().subtract(report.getTotalCost()));
			
			reports.add(report);
		}

		if(manualCosts != null && !manualCosts.isEmpty()) {
			BigDecimal manualTotalCost = manualCosts.stream()
					.map(c -> c.getAmount() != null ? c.getAmount() : BigDecimal.ZERO)
					.reduce(BigDecimal.ZERO, BigDecimal::add);
			ReportDTO manualReport = new ReportDTO();
			manualReport.setProductName("其他非商品成本");
			manualReport.setUnitCost(null);
			manualReport.setRevenue(null);
			manualReport.setTotalCost(manualTotalCost);
			manualReport.setGrossProfit(null);
			//獨立顯示其他成本總和
			reports.add(manualReport);
		}
		return reports;
	}

	
}
