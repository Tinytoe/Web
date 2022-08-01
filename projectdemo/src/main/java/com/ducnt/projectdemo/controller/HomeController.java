package com.ducnt.projectdemo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.ducnt.projectdemo.entity.Category;
import com.ducnt.projectdemo.entity.Product;
import com.ducnt.projectdemo.repo.CategoryRepo;
import com.ducnt.projectdemo.repo.ProductRepo;
import com.ducnt.projectdemo.repo.UserRepo;

@Controller
public class HomeController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@GetMapping("/client-show-product")
	public String test(Model model, HttpSession session) {
		
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
	
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		model.addAttribute("categoryList", categoryRepo.findAll());
//		model.addAttribute("productList", productRepo.findAll());
		
		//listThit
		
//		List<Product> productsThit  =  productRepo.findByCategorName("Thit");
//		List<Product> thits = new ArrayList<Product>();
//		for (int i = 0 ;i<3; i ++) {
//			thits.add(productsThit.get(i));
//		}
//		model.addAttribute("productsThit", thits);
		
		//listThit
		
//		List<Product> productsTieudung  =  productRepo.findByCategorName("Tieu dung");
//		List<Product> tieuDungs = new ArrayList<Product>();
//		for (int i = 0 ;i<3; i ++) {
//			tieuDungs.add(productsTieudung.get(i));
//		}
//		model.addAttribute("productsTieuDung", tieuDungs);
//		
		
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
		
		return "index.html";
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
		long sumTT = 0;
		session.setAttribute("sumTT", 0);
		session.removeAttribute("coupon");
		session.removeAttribute("user");
		session.removeAttribute("order");
		
	}
	
	@GetMapping("/admin/quan-li-shop")
	public String index(HttpSession session, Model model) {
		UserDetails userDetails = null;
		Object b =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (b!="anonymousUser") {
			userDetails = (UserDetails) b;
		}
		if (userDetails != null) {
			session.setAttribute("user", userRepo.findByUsername(userDetails.getUsername()));
			model.addAttribute("user", session.getAttribute("user"));
		}
		return "/admin/quan-li-shop.html";
	}
	
}
