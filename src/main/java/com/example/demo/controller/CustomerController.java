package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.entity.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/find-by-phone")
	public ResponseEntity<?> getCustomerByPhone(@RequestParam String phone){
		 return customerRepository.findByPhone(phone)
				 .<ResponseEntity<?>>map(ResponseEntity::ok)
				 .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
	    if (customerRepository.findByPhone(customer.getPhone()).isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 避免重複
	    }
	    Customer saved = customerRepository.save(customer);
	    return ResponseEntity.ok(saved);
	}

	
	//模糊搜尋方法
	@GetMapping("/autocomplete")
	public ResponseEntity<List<Customer>> autocomplete(@RequestParam String keyword) {
		List<Customer> result = customerRepository.findByPhoneContainingOrNameContaining(keyword, keyword);
		return ResponseEntity.ok(result);
	}
	
}
