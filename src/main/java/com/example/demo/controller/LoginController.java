package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	//顯示登入頁面，處理 GET /login 請求，回傳 login.jsp 頁面
	@GetMapping("/login")
	public String loginPage(@RequestParam (required = false)String role, Model model) {
		model.addAttribute("role", role);
		return "onion/login"; //login.html會使用role來決定畫面顯示
	}
	//檢查登入資訊
	@PostMapping("/login")
	public String checkLogin(@RequestParam String account, 
							 @RequestParam String password,
							 @RequestParam String role,
							 Model model, HttpSession session) {
		
		User user = userRepository.findByAccount(account);
		if( user !=  null && BCrypt.checkpw(password, user.getPassword())){
			//確認角色是否和前端role相符
			if(!role.equals(user.getRole())) {
				model.addAttribute("error", "帳號與角色不符");
				model.addAttribute("role", role);	//登入失敗會重新回到登入頁面，登入頁面可能會根據 role 參數去調整畫面
				return "onion/login";
			}
			// 將 Entity 轉成 DTO 存入 Session
			UserDTO userDTO = userMapper.toDTO(user);
			session.setAttribute("loginUser", userDTO);
			
			//根據角色不同導入不同頁面
			if("admin".equals( user.getRole())) {
				return "redirect:/admin/home";
			}else {
				return "redirect:/user/home";
			}
		}
		//登入失敗，回到登入頁並帶錯誤訊息
		model.addAttribute("error", "帳號或密碼錯誤");
		return "onion/login";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
		
	}
}