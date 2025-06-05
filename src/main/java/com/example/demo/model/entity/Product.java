package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "product_id")
	    private Long id;
	 	@Column(name = "product_name")
	    private String name;

	    private BigDecimal price;

	    private String category;

	    private BigDecimal cost;
	    @Column(name = "image_path") //記得要寫才會對應到資料庫
	    private String imagePath;
	    
	    @OneToMany(mappedBy = "product")
	    private List<OrderItem> orderItems;
	}

