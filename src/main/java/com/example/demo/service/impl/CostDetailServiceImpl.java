package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.mapper.CostDetailMapper;
import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.model.entity.CostDetail;
import com.example.demo.repository.CostDetailRepository;
import com.example.demo.service.CostDetailService;
@Service
public class CostDetailServiceImpl implements CostDetailService{

	@Autowired
	private CostDetailRepository costDetailRepository;
	
	@Autowired
	private CostDetailMapper mapper;
	
	@Override
	public List<CostDetailDTO> getAllCost() {
		return costDetailRepository.findAll()
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public CostDetailDTO getById(Long id) {
		return costDetailRepository.findById(id)
				.map(mapper::toDTO)
				.orElseThrow(() -> new NoResourceFoundException("成本ID" + id + "未找到"));
		
	}

	@Override
	public List<CostDetailDTO> getCostByDate(LocalDate date) {
		return costDetailRepository.findCostDetailByDate(date)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public List<CostDetailDTO> getCostByYearAndMonth(int year, int month) {
		return costDetailRepository.findByYearAndMonth(year, month)
				.stream()
				.map(mapper::toDTO)
				.toList();
	}

	@Override
	public CostDetailDTO createCost(CostDetailDTO costDetailDTO) {
		//DTO轉Entity
		CostDetail entity = mapper.toEntity(costDetailDTO);
		//存速資料庫
		CostDetail saved = costDetailRepository.save(entity);
		//Entity轉DTO
		return mapper.toDTO(saved);
	}

	@Override
	public CostDetailDTO updateCost(Long id, CostDetailDTO costDetailDTO) {
		//findById(id) 回傳的是 Optional<CostDetail>
		//只要呼叫了 Optional 的方法（即使沒直接宣告變數），就是在用 Optional(map、orElseThrow、ifPresent、isPresent )
		CostDetail costDetail = costDetailRepository.findById(id)
				.orElseThrow(() -> new NoResourceFoundException("成本ID" + id + "未找到"));
		//更新欄位(從 DTO 拿資料存進 Entity)
		costDetail.setCategory(costDetailDTO.getCategory());
		costDetail.setItemName(costDetailDTO.getItemName());
		costDetail.setAmount(costDetailDTO.getAmount());
		costDetail.setDate(costDetailDTO.getDate());
		costDetail.setRemark(costDetailDTO.getRemark());
		//從 Entity 存進資料庫（Repository）
		CostDetail saved = costDetailRepository.save(costDetail);
		//從 Entity 轉回 DTO 回傳前端
		return mapper.toDTO(saved);
	}

	@Override
	public void delete(Long id) {
		if(!costDetailRepository.existsById(id)) {
			throw new NoResourceFoundException("成本ID" + id + "未找到");
		}
		costDetailRepository.deleteById(id);
	}

	
}
