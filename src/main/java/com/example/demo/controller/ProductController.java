package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exception.NoResourceFoundException;
import com.example.demo.model.dto.ProductDTO;
import com.example.demo.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/product") // 設定 base URL 前綴
public class ProductController {

	@Autowired
	private ProductService productService;

	// 商品列表
	@GetMapping("/list")
	public String listProducts(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) String success) {
		List<ProductDTO> productList = productService.findAll();
		model.addAttribute("productList", productList);
		model.addAttribute("error", error);
		model.addAttribute("success", success);
		return "onion/product_list"; // 對應 Thymeleaf 檔案
	}

	// 顯示新增表單
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new ProductDTO());
		return "onion/product_form";
	}

	// 提交新增商品
	@PostMapping("/add")
	public String addProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "onion/product_form";
		}
		productService.save(productDTO);
		return "redirect:/product/list?success=add";
	}

	// 顯示修改表單
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
		Optional<ProductDTO> optProduct = productService.findById(id);
		if (optProduct.isEmpty()) {
			return "redirect:/product/list?error=notfound";
		}
		model.addAttribute("product", optProduct.get());
		return "onion/product_form";
	}

	// 提交修改商品
	@PostMapping("/update")
	public String updateProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result) {
		if (result.hasErrors()) {
			return "onion/product_form";
		}
		productService.update(productDTO);
		return "redirect:/product/list?success=update";
	}

	// 刪除商品
	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		try {
			productService.delete(id);
			return "redirect:/product/list?success=delete";
		} catch (NoResourceFoundException e) {
			return "redirect:/product/list?error=notfound";
		}

	}
}
