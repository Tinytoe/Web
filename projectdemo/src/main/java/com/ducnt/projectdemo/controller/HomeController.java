package com.ducnt.projectdemo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectdemo.repo.ProductRepo;
import com.ducnt.projectdemo.repo.UserRepo;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		
		UserDetails userDetails = null;
		Object b =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (b!="anonymousUser") {
			userDetails = (UserDetails) b;
		}
		if (userDetails != null) {
			session.setAttribute("user", userRepo.findByUsername(userDetails.getUsername()));
			model.addAttribute("user", session.getAttribute("user"));
		}
			model.addAttribute("productList", productRepo.findAll());
			return "client/client-show-product.html";
	}

	@GetMapping("/change-lang")
	public void changeLang(HttpServletResponse resp, @RequestHeader("Referer") String refer) throws IOException {
		System.out.println(refer);
		resp.sendRedirect(refer);
	}

	@GetMapping("/dang-nhap")
	public String login(HttpServletRequest req, @RequestParam(name="err", required = false) String e) {
		if (e != null) {
			req.setAttribute("err", e);
		}
		return "login.html";
	}
	
	@GetMapping("/deny")
	public String deny() {
		return "deny.html";
	}
	
	@GetMapping("/dang-nhap?logout")
	public void logout(HttpSession session) {
		session.setAttribute("sumTT", null);
		session.setAttribute("coupon", null);
		session.setAttribute("user", null);
		session.setAttribute("order", null);
		
	}
	
	
}
