package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.dto.ReportDTO;
import com.example.demo.service.ReportService;

public interface ReportService {
	List<ReportDTO> getReportData(LocalDate date);
	
}
