package com.ducnt.projectdemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/bill/them-vao-gio-hang")
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
		return "redirect:/client/xem-gio-hang";
	}

	@GetMapping("/client/xem-gio-hang")
	public String viewCart(HttpSession session, Model model) {
		if (session.getAttribute("cart") != null) {
			Order order = (Order) session.getAttribute("cart");
			model.addAttribute("order", order);
			model.addAttribute("sumTT", session.getAttribute("sumTT"));

		} else
			model.addAttribute("sumTT", null);
		model.addAttribute("user", session.getAttribute("user"));
		return "/client/gio-hang.html";
	}

	@PostMapping("/client/create-bill")
	public String creatBill(HttpSession session, @RequestParam(name = "userId") int userId) {

		return "/client/gio-hang.html";
	}

	@PostMapping("/client/update-cart")
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
		return "redirect:/client/xem-gio-hang";
	}

	@PostMapping("/client/check-couponcode")
	public String checkCouponCode(@RequestParam(name = "couponCodeInput") String couponCodeInput, HttpSession session) {

		Coupon coupon = couponRepo.searchByCouponCode(couponCodeInput);
		
		coupon.setCouponCode(couponCodeInput);
		
		if(coupon.getExpiredDate().after(new Date())) {
			session.setAttribute("coupon", coupon);
		}
		
		return "redirect:/client/payment";
	}
	
}
