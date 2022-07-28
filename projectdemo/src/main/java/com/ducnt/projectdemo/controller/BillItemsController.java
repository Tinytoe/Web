package com.ducnt.projectdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectdemo.entity.BillItems;
import com.ducnt.projectdemo.repo.BillItemsRepo;
import com.ducnt.projectdemo.repo.BillRepo;
import com.ducnt.projectdemo.repo.ProductRepo;

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
	public String searchBillItems(Model model) {
		

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
