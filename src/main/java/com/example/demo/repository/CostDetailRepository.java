package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.CostDetail;

public interface CostDetailRepository extends JpaRepository<CostDetail, Long> {
    List<CostDetail> findByMonth(String month);
}