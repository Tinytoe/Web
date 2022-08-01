package com.ducnt.projectdemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectdemo.entity.Coupon;
import com.ducnt.projectdemo.entity.Order;
import com.ducnt.projectdemo.entity.OrderItem;
import com.ducnt.projectdemo.entity.Product;
import com.ducnt.projectdemo.repo.CouponRepo;
import com.ducnt.projectdemo.repo.ProductRepo;

@Controller
public class CartController {
	@Autowired
	ProductRepo productRepo;

	@Autowired
	CouponRepo couponRepo;

	private double sumTT = 0;

	@GetMapping("/cart/them-vao-gio-hang")
	public String addToCart(@RequestParam(name = "productId") int productId,
			@RequestParam(name = "quantityInput") int quantityInput, Model model, HttpSession session) {

		Product product = productRepo.getById(productId);

		if (session.getAttribute("cart") == null) {

			Order order = new Order();

			OrderItem orderItem = new OrderItem();
			orderItem.setNumber(quantityInput);
			orderItem.setProduct(product);

			sumTT += orderItem.getProduct().getPrice() * orderItem.getNumber();
			List<OrderItem> items = new ArrayList<OrderItem>();
			items.add(orderItem);

			order.setItemDTOs(items);

			session.setAttribute("cart", order);
			model.addAttribute("order", order);
			System.out.println(sumTT);

		} else {
			Order order = (Order) session.getAttribute("cart");
			List<OrderItem> items = order.getItemDTOs();

			boolean flag = false;
			for (OrderItem item : items) {
				if (item.getProduct().getId() == product.getId()) {
					item.setNumber(item.getNumber() + quantityInput);

					flag = true;
					sumTT += quantityInput * product.getPrice();
				}
			}
			if (!flag) {
				// neu trong gio hang chua co san pham

				OrderItem orderItem = new OrderItem();
				orderItem.setNumber(quantityInput);

				orderItem.setProduct(product);

				items.add(orderItem);

				order.setItemDTOs(items);
				sumTT += orderItem.getProduct().getPrice() * quantityInput;

			}
			session.setAttribute("cart", order);

			model.addAttribute("order", order);
		}
		session.setAttribute("sumTT", sumTT);
		return "redirect:/cart/xem-gio-hang";
	}

	@PostMapping("/cart/update-cart")
	public String updatecart(@RequestParam(name = "prId") int id, @RequestParam(name = "count") int count,
			HttpSession session) {

		Order order = (Order) session.getAttribute("cart");
		List<OrderItem> items = order.getItemDTOs();
		for (OrderItem item : items) {
			if (item.getProduct().getId() == id) {
				sumTT = sumTT - item.getNumber() * item.getProduct().getPrice() + count * item.getProduct().getPrice();
				item.setNumber(count);

			}
		}

		session.setAttribute("sumTT", sumTT); 
		return "redirect:/cart/xem-gio-hang";
	}
	
	@GetMapping("/cart/xem-gio-hang")
	public String viewCart(HttpSession session, Model model) {
		
		if (session.getAttribute("cart") != null) {
			Order order = (Order) session.getAttribute("cart");
			model.addAttribute("order", order);
			model.addAttribute("sumTT", session.getAttribute("sumTT"));

		} else {
			sumTT=0;
			session.setAttribute("sumTT",sumTT);
			model.addAttribute("sumTT", session.getAttribute("sumTT"));
		} 
		 if (session.getAttribute("user") == null) {
				session.setAttribute("sumTT",sumTT);
				model.addAttribute("sumTT", session.getAttribute("sumTT"));
		 } else
			
		model.addAttribute("user", session.getAttribute("user"));
		return "/cart/gio-hang.html";
	}
	@PostMapping("/cart/check-couponcode")
	public String checkCouponCode(@RequestParam(name = "couponCodeInput", required = false) String couponCodeInput, HttpSession session) {

		Coupon coupon = couponRepo.searchByCouponCode(couponCodeInput);
		
		coupon.setCouponCode(couponCodeInput);
		
		if(coupon.getExpiredDate().after(new Date())) {
			session.setAttribute("coupon", coupon);
		}
		
		return "redirect:/client/cart/payment";
	}
	
	@GetMapping("/cart/xoa-khoi-gio-hang/{productId}")
	public String removeFromCart(@PathVariable(name = "productId") int productId,
								Model model, HttpSession session) {
		
		
		Order order = (Order) session.getAttribute("cart");
		List<OrderItem> orderItems = order.getItemDTOs();
		
		Iterator<OrderItem> iterator =  orderItems.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getProduct().getId() == productId) {
				iterator.remove();
			}
		}
		if(orderItems.isEmpty()) {
			session.removeAttribute("cart");
		}
		for (OrderItem item : orderItems) {
			sumTT = item.getNumber() * item.getProduct().getPrice();
		}
		session.setAttribute("sumTT", sumTT); 
		model.addAttribute("order", order);
		
		return "redirect:/cart/xem-gio-hang";
	}
	
	
}
