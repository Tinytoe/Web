package com.ducnt.project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.project3.entity.BillItems;
import com.ducnt.project3.repo.BillItemsRepo;
import com.ducnt.project3.repo.BillRepo;
import com.ducnt.project3.repo.ProductRepo;

@Controller
public class BillItemsController {

	@Autowired
	BillRepo billRepo;

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	BillItemsRepo billItemsRepo;
	
	
	@GetMapping("/billItems/create")
	public String create(Model model) {
		
		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("billList", billRepo.findAll());
		return "billItems/create.html";
	}

	@PostMapping("/billItems/create")
	public String createBillItem(@ModelAttribute BillItems billItems) {
		billItemsRepo.save(billItems);
		return "redirect:/billItems/search";
	}

	@PostMapping("/billitems/search")
	public String search(Model model, @RequestParam(name = "size", required = false) String size,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "name", required = false) String name) {
		
//		int currentSize = 5;
//		int currentPage = 0;
//
//		if (StringUtils.hasText(size)) {
//			currentSize = Integer.parseInt(size);
//		}
//
//		if (StringUtils.hasText(page)) {
//			currentPage = Integer.parseInt(page);
//		}
//
//		Pageable pageable = PageRequest.of(currentPage, currentSize);

//		if (StringUtils.hasText(name)) {
//			Page<BillItems> pageBillItems = billItemsRepo.searchByName("%" + name + "%", pageable);
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//			model.addAttribute("userList", pageUser.getContent());
//		}
//		if (StringUtils.hasText(username)) {
//			Page<User> pageUser = userRepo.searchByUserName("%" + username + "%", pageable);
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//			model.addAttribute("userList", pageUser.getContent());
//		} else {
//
//			Page<User> pageUser = userRepo.findAll(pageable);
//
//			model.addAttribute("totalPage", pageUser.getTotalPages());
//			model.addAttribute("userList", pageUser.getContent());
//		}

		model.addAttribute("name", name);
		return "billItems/search.html";
	}

	@GetMapping("/billItems/search")
	public String search(Model model) {
		model.addAttribute("billItemsList", billItemsRepo.findAll());
		return "/billItems/search.html";
	}

	@GetMapping("/billItems/delete")
	public String delete(@RequestParam("id") int id) {
		billItemsRepo.deleteById(id);
		return "redirect:/billItems/search";
	}

	@GetMapping("/billItems/edit")
	public String edit(Model model, @RequestParam("id") int id) {
		model.addAttribute("billItems", billItemsRepo.findById(id).orElse(null));
		return "billItems/edit.html";
	}

	@PostMapping("/billItems/edit")
	public String edit(@ModelAttribute("user") @Validated BillItems billItems, BindingResult bindingResult) {

		billItemsRepo.save(billItems);
		return "redirect:/user/search";
	}

}
