package com.ducnt.projectAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ducnt.projectAPI.repo.ProductRepo;

@Controller
public class ClientController {

	@Autowired
	ProductRepo productRepo;
	
	
	@GetMapping("/client/show-product")
	public String listProduct(Model model) {
		model.addAttribute("productList", productRepo.findAll());
		return "client/client-show-product.html";
	}
	
}
