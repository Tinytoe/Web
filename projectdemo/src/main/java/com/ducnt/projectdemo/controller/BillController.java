package com.ducnt.projectdemo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectdemo.entity.Bill;
import com.ducnt.projectdemo.entity.BillItems;
import com.ducnt.projectdemo.entity.Coupon;
import com.ducnt.projectdemo.entity.Order;
import com.ducnt.projectdemo.entity.OrderItem;
import com.ducnt.projectdemo.entity.Product;
import com.ducnt.projectdemo.entity.User;
import com.ducnt.projectdemo.model.MessageDTO;
import com.ducnt.projectdemo.model.ThongKeDTO;
import com.ducnt.projectdemo.repo.BillItemsRepo;
import com.ducnt.projectdemo.repo.BillRepo;
import com.ducnt.projectdemo.repo.CouponRepo;
import com.ducnt.projectdemo.repo.ProductRepo;
import com.ducnt.projectdemo.repo.UserRepo;
import com.ducnt.projectdemo.service.MailService;

@Controller
public class BillController {

	@Autowired
	BillRepo billRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	BillItemsRepo billItemsRepo;

	@Autowired
	MailService mailService;

	@Autowired
	CouponRepo couponRepo;

//	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
//	@RolesAllowed("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/bill/create")
	public String create(Model model) {
		model.addAttribute("userList", userRepo.findAll());
		return "bill/create.html";
	}

	@PostMapping("/bill/search")
	public String search(Model model, @RequestParam(name = "size", required = false) String size,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "x", required = false) String x) {

		int currentSize = 5;
		int currentPage = 0;

		if (StringUtils.hasText(size)) {
			currentSize = Integer.parseInt(size);
		}

		if (StringUtils.hasText(page)) {
			currentPage = Integer.parseInt(page);
		}

		Pageable pageable = PageRequest.of(currentPage, currentSize);

		if (StringUtils.hasText(x)) {
		} else {

			Page<Bill> pageBill = billRepo.findAll(pageable);

			model.addAttribute("totalPage", pageBill.getTotalPages());
			model.addAttribute("billList", pageBill.getContent());
		}

		model.addAttribute("name", name);

		return "bill/search.html";
	}

	@GetMapping("/bill/thongke")
	public String thongKeTheoUser(Model model) {
		model.addAttribute("billList", billRepo.findAll());
		return "/bill/search.html";
	}

	@PostMapping("/bill/thongke")
	public String thongKeTheoBuyDate(@RequestParam(name = "from", required = false) String from,
			@RequestParam(name = "to", required = false) String to, Model model) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dayfrom = sdf.parse(from);
		Date dayto = sdf.parse(to);

		model.addAttribute("billList", billRepo.thongKeTheoNgay(dayfrom, dayto));
		return "/bill/thong-ke-theo-ngay.html";
	}

	@PostMapping("/bill/thong-ke-theo-user")
	public String thongKeTheoUser(@RequestParam(name = "x", required = false) String x, Model model)
			throws ParseException {
		List<ThongKeDTO> list = new ArrayList<>();
		List<Object[]> objects = billRepo.thongKeTheoUser(Integer.parseInt(x));

		for (Object[] obj : objects) {

			ThongKeDTO thongKe = new ThongKeDTO();
			thongKe.setId((Integer) obj[0]);
			thongKe.setSl((Long) obj[1]);
			list.add(thongKe);
		}
		model.addAttribute("list", list);
		return "/bill/thong-ke-theo-user.html";
	}

	@GetMapping("/bill/thong-ke-theo-thang")
	public String thongKeTheoThang(Model model) {
		List<ThongKeDTO> list = new ArrayList<>();
		List<Object[]> objects = billRepo.thongKeTheoThang();

		for (Object[] obj : objects) {

			ThongKeDTO thongKe = new ThongKeDTO();
			thongKe.setThang((Integer) obj[0]);
			thongKe.setSl((Long) obj[1]);
			list.add(thongKe);
		}
		model.addAttribute("list", list);
		return "/bill/thong-ke-theo-thang.html";
	}

	@GetMapping("/bill/search")
	public String search(Model model) {
		model.addAttribute("billList", billRepo.findAll());

		return "/bill/search.html";
	}

	@GetMapping("/bill/delete")
	public String delete(@RequestParam("id") int id) {
		billRepo.deleteById(id);
		return "redirect:/bill/search";
	}

	@GetMapping("/bill/edit")
	public String edit(@RequestParam("id") int id, Model model) {

		model.addAttribute("bill", billRepo.findById(id).orElse(null));
		model.addAttribute("userList", userRepo.findAll());
		return "/bill/edit.html";
	}

	@PostMapping("/bill/edit")
	public String edit(@ModelAttribute Bill bill) {

//		Bill category2 = categoryRepo.findById(category.getId()).orElse(null);
//		category2.setName(category.getName());
		billRepo.save(bill);
		return "redirect:/bill/search";
	}

	@PostMapping("/bill/create")
	public String create(HttpSession session) {
		Bill bill = new Bill();
		bill.setBuyDate(new Date());
		User user = (User) session.getAttribute("user");
		Coupon coupon = (Coupon) session.getAttribute("coupon");
		if (coupon != null) {
			bill.setCouponCode(coupon.getCouponCode());
			bill.setDiscount(coupon.getDiscountAmount());
		} else {
			bill.setCouponCode(null);
			bill.setDiscount(0);
		}

		bill.setUser(user);

		billRepo.save(bill);
		System.out.println(bill.getId());

		Order order = (Order) session.getAttribute("cart");
		for (OrderItem orderItem : order.getItemDTOs()) {
			BillItems billItems = new BillItems();
			billItems.setBill(bill);
			billItems.setProduct(orderItem.getProduct());
			billItems.setQuantity(orderItem.getNumber());
			billItems.setBuyPrice(orderItem.getProduct().getPrice());
			billItemsRepo.save(billItems);
		}
		
		

		// send mail
//		MessageDTO messageDTO = new MessageDTO();
//		messageDTO.setToName(user.getName());
//		messageDTO.setTo(user.getEmail());
//		try {
//			mailService.sendEmail(messageDTO);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}
		return "redirect:/client/payment-success";
	}
}
