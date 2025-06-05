package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.dto.CostDetailDTO;

public interface CostDetailService {

	public List<CostDetailDTO> getAllCost();
	public CostDetailDTO getById(Long id);
	public List<CostDetailDTO> getCostByDate(LocalDate date);
	public List<CostDetailDTO> getCostByYearAndMonth(int year, int month);
	public CostDetailDTO createCost(CostDetailDTO costDetailDTO);
	public CostDetailDTO updateCost(Long id, CostDetailDTO costDetailDTO);
	public void delete(Long id);
}
