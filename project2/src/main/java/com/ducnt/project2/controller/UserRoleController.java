package com.ducnt.project2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.project2.dto.UserRole;
import com.ducnt.project2.repo.UserRoleRepo;

@Controller
public class UserRoleController {

	@Autowired
	UserRoleRepo userRoleRepo;

	@GetMapping("/userrole/create")
	public String create() {
		return "userrole/create.html";
	}

	@PostMapping("/userrole/create")
	public String create(@ModelAttribute UserRole userRole) {

		userRoleRepo.save(userRole);
		return "redirect:/userrole/search";
	}

	@GetMapping("/userrole/search")
	public String search(Model model) {

		model.addAttribute("userRoleList", userRoleRepo.findAll());
		return "/userrole/search.html";
	}

	

	@GetMapping("/userrole/delete")
	public String delete(@RequestParam("userId") int userId) {

		userRoleRepo.deleteById(userId);

		return "redirect:/userrole/search";
	}
	
	@GetMapping("/userrole/edit")
	public String edit(Model model, @RequestParam("userId") int userId) {

		model.addAttribute("userRole", userRoleRepo.findById(userId).orElse(null));

		return "userrole/edit.html";
	}

	@PostMapping("/userrole/edit")
	public String edit(@ModelAttribute("userRole") @Validated UserRole userRole, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "/userrole/edit.html";

		}
		userRoleRepo.save(userRole);
		return "redirect:/userrole/search";
	}

}
