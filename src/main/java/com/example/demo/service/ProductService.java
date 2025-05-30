package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.dto.ProductDTO;

public interface ProductService {
    List<ProductDTO> findAll();
    Optional<ProductDTO> findById(Long id);
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO);
    void delete(Long id);
}
