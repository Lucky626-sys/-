package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.model.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper mapper;

    
    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
            .stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findById(Long id) {
        return productRepository.findById(id).map(mapper::toDTO);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
    	if(productDTO == null) {
    		//繼承自 RuntimeException，屬於非受檢異常，不需要在方法簽名中聲明 throws
    		throw new IllegalArgumentException("productDTO不可為空");
    	}
    	Product saved = productRepository.save(mapper.toEntity(productDTO));
        return mapper.toDTO(saved);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) {
        if(productDTO.getId() == null || !productRepository.existsById(productDTO.getId())) {
        	throw new NoResourceFoundException("商品 ID" + productDTO.getId() + "未找到");
        }
        Product updated = productRepository.save(mapper.toEntity(productDTO));
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
    	if(!productRepository.existsById(id)) {
    		throw new NoResourceFoundException("商品 ID" + id + "未找到");
    	}
        productRepository.deleteById(id);
    }

}
