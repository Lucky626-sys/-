//自動統計API
/*package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.dto.DailySalesDTO;
import com.example.demo.service.AutoSalesService;

@RestController
@RequestMapping("/sales/auto")
public class AutoSalesController {

    @Autowired
    private AutoSalesService service;

    @GetMapping
    public List<DailySalesDTO> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return service.getDailySalesByDate(date);
    }
}
*/