package com.ducnt.projectdemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ducnt.projectdemo.entity.Coupon;
import com.ducnt.projectdemo.entity.Order;
import com.ducnt.projectdemo.entity.User;
import com.ducnt.projectdemo.repo.ProductRepo;
import com.ducnt.projectdemo.repo.UserRepo;

@Controller
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/cart/payment")
	public String payment(HttpSession session, Model model) {
		
		UserDetails userDetails = null;
		Object b =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (b!="anonymousUser") {
			userDetails = (UserDetails) b;
		}
		if (userDetails != null) {
			session.setAttribute("user", userRepo.findByUsername(userDetails.getUsername()));
			model.addAttribute("user", session.getAttribute("user"));
		} else {
			session.setAttribute("sumTT",0);
		}
		
		model.addAttribute("coupon",session.getAttribute("coupon"));
		model.addAttribute("user",session.getAttribute("user"));
		model.addAttribute("order", session.getAttribute("cart"));
		model.addAttribute("sumTT", session.getAttribute("sumTT"));
		return "cart/payment.html";
	}
	
	@GetMapping("/register")
	public String register(HttpSession session) {
		session.setAttribute("success", 0);
		return "client/register.html";
	}
	@GetMapping("/payment-success")
	public String done() {
		return "client/payment-success.html";
	}
	
	@PostMapping("/register")
	public String createUser(@ModelAttribute User user, HttpSession session, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return "redirect:/login";
	}
}
