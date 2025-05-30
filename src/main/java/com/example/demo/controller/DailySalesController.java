// 手動輸入 營業額
package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.mapper.DailySalesMapper;
import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.model.entity.DailySales;
import com.example.demo.repository.DailySalesRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sales/manual")
public class DailySalesController {

    @Autowired
    private DailySalesRepository repository;
    
    @Autowired
    private DailySalesMapper mapper;

    //接收 DTO，轉 Entity，存資料庫，回傳 DTO
    @PostMapping
    public DailySalesDTO add(@Valid @RequestBody DailySalesDTO dto) {
        DailySales entity = mapper.toEntity(dto);
        DailySales saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    //從資料庫取所有 Entity，轉成 DTO，回傳清單
    @GetMapping("/all")
    public List<DailySalesDTO> getAll() {
        List<DailySales> dailysales = repository.findAll();
        return dailysales.stream()
        				 .map(mapper::toDTO)	//把 Stream 裡的每個 Entity 物件轉成 DTO
        				 .toList();
    }
}
