//POS機首頁
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.dto.UserDTO;

import jakarta.servlet.http.HttpSession;


@Controller
public class IndexController {
	@GetMapping("/")
	public String home(HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("loginUser");
		if(user != null) {
			model.addAttribute("username", user.getAccount());
			model.addAttribute("role", user.getRole());
		}
		//	回傳邏輯視圖名稱，Spring 找 index.html 模板
		return "index";
	}
}