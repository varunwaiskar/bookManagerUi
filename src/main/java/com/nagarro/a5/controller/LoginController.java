package com.nagarro.a5.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.a5.entity.User;
import com.nagarro.a5.service.UserAuthService;

@Controller
public class LoginController {

	private UserAuthService userService;

	@Autowired
	public LoginController(UserAuthService userService) {
		this.userService = userService;
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/")
	public String home() {
		return "login.jsp";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = new User(name, password);

		if(userService.authenticateUser(user))
			System.out.println("ok");
		else {
			System.out.println("not ok");
			return "/";
		}
		
		return "/book";
		
	}
	
}
