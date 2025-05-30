package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.entity.DailySales;

@Component
public class DailySalesMapper {
	@Autowired
	private ModelMapper modelMapper;
	;
	public DailySalesDTO toDTO(DailySales dailySales) {
		return modelMapper.map(dailySales, DailySalesDTO.class);
	}
	
	public DailySales toEntity(DailySalesDTO dailySalesDTO) {
		return modelMapper.map(dailySalesDTO, DailySales.class);
	}


}
