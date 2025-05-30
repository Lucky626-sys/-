package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Product;
@Component
public class ProductMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProductDTO toDTO(Product product) {
		return modelMapper.map(product, ProductDTO.class);
		
	}
	
	public Product toEntity(ProductDTO productDTO) {
		return modelMapper.map(productDTO, Product.class);
		
	}
}
