package com.ducnt.projectdemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ducnt.projectdemo.entity.Coupon;
import com.ducnt.projectdemo.repo.ProductRepo;

@Controller
public class ClientController {

	@Autowired
	ProductRepo productRepo;
	
	
	@GetMapping("/client/show-product")
	public String listProduct(Model model, HttpSession session) {
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("user", session.getAttribute("user"));
		return "client/client-show-product.html";
	}
	
	@GetMapping("/client/payment")
	public String payment(HttpSession session, Model model) {
		
		model.addAttribute("coupon",session.getAttribute("coupon"));
		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("order", session.getAttribute("cart"));
		model.addAttribute("sumTT", session.getAttribute("sumTT"));
		return "client/payment.html";
	}
	
}
