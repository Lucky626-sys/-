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
	
	public DailySalesDTO toDTO(DailySales dailySales) {
		DailySalesDTO dto = modelMapper.map(dailySales, DailySalesDTO.class);
		//Profit是計算欄位，手動set 確保值正確
		dto.setProfit(dailySales.getProfit()); //Entity裡面的getProfit已經處理null的問題了
		return dto;
	}
	
	public DailySales toEntity(DailySalesDTO dailySalesDTO) {
		return modelMapper.map(dailySalesDTO, DailySales.class);
	}

	
	/*
	 * modelMapper.getConfiguration().setPropertyCondition(context ->!context.getMapping().getLastDestinationProperty().isAnnotationPresent(Transient.class));
	 *
	 */

}
