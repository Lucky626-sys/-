package com.example.demo.model.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")

public class User {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "account")
	private String account;
	private String nickname;
	private String passwordHash;
	private String role;
	
	
	

}
