package com.example.demo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.dto.CustomerDTO;
import com.example.demo.model.entity.Customer;

@Component
public class CustomerMapper {

	@Autowired 
	private ModelMapper mapper;
	
	public CustomerDTO toDTO(Customer customer) {
		return mapper.map(customer, CustomerDTO.class);
	}
	
	public Customer toEntity(CustomerDTO customerDTO) {
		return mapper.map(customerDTO, Customer.class);
	}
}
