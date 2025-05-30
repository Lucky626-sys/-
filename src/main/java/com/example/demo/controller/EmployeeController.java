package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/user")
public class EmployeeController {
	@GetMapping("/home")
	public String userHome(HttpSession session, Model model) {
		//session.setAttribute("loginUser", userDTO); 放入的是 UserDTO 物件，但 getAttribute 取出時是 Object
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		if (loginUser != null) {
			System.out.println("登入使用者：" + loginUser.getAccount());
		    model.addAttribute("account", loginUser.getAccount());
		    model.addAttribute("role", loginUser.getRole());
		    model.addAttribute("nickname", loginUser.getNickname());  // 新增這行
		}else {
	        System.out.println("Session 中沒有 loginUser");
	        //redirect: 冒號後面通常要加 /，表示重定向的目標是從網站根目錄開始的絕對路徑
	        return "redirect:/login";
	    }
		return "onion/user_home";
		
	}
}


