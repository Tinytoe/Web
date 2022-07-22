package com.ducnt.project3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String home() {
		return "home.html";
	}

	@GetMapping("/change-lang")
	public void changeLang(HttpServletResponse resp, @RequestHeader("Referer") String refer) throws IOException {
		System.out.println(refer);
		resp.sendRedirect(refer);
	}

	@GetMapping("/dang-nhap")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("/deny")
	public String deny() {
		return "deny.html";
	}
	
	
}
