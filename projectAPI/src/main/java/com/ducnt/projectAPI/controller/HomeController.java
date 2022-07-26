package com.ducnt.projectAPI.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ducnt.projectAPI.repo.ProductRepo;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/home")
	public String home(Model model) {
			model.addAttribute("productList", productRepo.findAll());
			return "client/client-show-product.html";
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
