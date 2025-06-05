package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.CostDetailDTO;
import com.example.demo.model.entity.CostDetail;

@Component
public class CostDetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	public CostDetailDTO toDTO(CostDetail costDetail) {
		CostDetailDTO dto =  mapper.map(costDetail, CostDetailDTO.class);
		if(costDetail.getCategory() != null) {
			dto.setCategoryDisplayName(costDetail.getCategory().getDisplayName());
		}
		return dto;
	}
	
	public CostDetail toEntity(CostDetailDTO costDetailDTO) {
		return mapper.map(costDetailDTO, CostDetail.class);
	}
}
