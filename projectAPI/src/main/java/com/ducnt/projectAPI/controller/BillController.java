package com.ducnt.projectAPI.controller;

import java.util.Date;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectAPI.entity.Bill;
import com.ducnt.projectAPI.entity.MessageDTO;
import com.ducnt.projectAPI.entity.User;
import com.ducnt.projectAPI.repo.BillRepo;
import com.ducnt.projectAPI.repo.UserRepo;
import com.ducnt.projectAPI.service.MailService;

@Controller
public class BillController {
	
	@Autowired
	BillRepo billRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MailService mailService;
	
	@GetMapping("/bill/create")
	public String create(Model model) {
		model.addAttribute("userList", userRepo.findAll());
		return "bill/create.html";
	}
	
	@PostMapping("/bill/create")
	public String create(@ModelAttribute Bill bill
						 ) {
		
		bill.setBuyDate(new Date());
		
		User user1 = userRepo.findById(bill.getUser().getId()).orElse(null);
		
		billRepo.save(bill);
		
		//send mail
		MessageDTO messageDTO = new MessageDTO();
		messageDTO.setToName(user1.getName());
		messageDTO.setTo(user1.getEmail());
		try {
			mailService.sendEmail(messageDTO);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "redirect:/bill/search";
	}
	
	@PostMapping("/bill/search")
	public String search(Model model, @RequestParam(name = "size", required = false) String size,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "name", required = false) String name) {

		int currentSize = 5;
		int currentPage = 0;

		if (StringUtils.hasText(size)) {
			currentSize = Integer.parseInt(size);
		}

		if (StringUtils.hasText(page)) {
			currentPage = Integer.parseInt(page);
		}

		Pageable pageable = PageRequest.of(currentPage, currentSize);

		if (StringUtils.hasText(name)) {
//			Page<Bill> pageBill = billRepo.searchByName("%" + name + "%", pageable);
//			model.addAttribute("totalPage", pageBill.getTotalPages());
//			model.addAttribute("billList", pageBill.getContent());
		}
		 else {

			Page<Bill> pageBill = billRepo.findAll(pageable);

			model.addAttribute("totalPage", pageBill.getTotalPages());
			model.addAttribute("billList", pageBill.getContent());
		}

		model.addAttribute("name", name);
		
		return "bill/search.html";
	}
	
	@GetMapping("/bill/search")
	public String search(Model model) {
		model.addAttribute("billList", billRepo.findAll());
		
		return "/bill/search";
	}
	
	@GetMapping("/bill/delete")
	public String delete(@RequestParam("id") int id) {
		billRepo.deleteById(id);
		return "redirect:/bill/search";
	}
	
	@GetMapping("/bill/edit")
	public String edit( @RequestParam("id") int id, Model model) {
		
		model.addAttribute("bill", billRepo.findById(id).orElse(null));
		model.addAttribute("userList", userRepo.findAll());
		return "/bill/edit.html";
	}
	
	@PostMapping("/bill/edit")
	public String edit( @ModelAttribute Bill bill) {
		
//		Bill category2 = categoryRepo.findById(category.getId()).orElse(null);
//		category2.setName(category.getName());
		billRepo.save(bill);
		return "redirect:/bill/search";
	}
	
}
