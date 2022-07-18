package com.ducnt.projectAPI.controller;

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

import com.ducnt.projectAPI.entity.Category;
import com.ducnt.projectAPI.repo.CategoryRepo;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@GetMapping("/category/create")
	public String create() {
		return "category/create.html";
	}
	
	@PostMapping("/category/create")
	public String create(@ModelAttribute Category category) {
		categoryRepo.save(category);
		return "redirect:/category/search";
	}
	
	@PostMapping("/category/search")
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
//			Page<Category> pageCategory = categoryRepo.searchByName("%" + name + "%", pageable);
//			model.addAttribute("totalPage", pageCategory.getTotalPages());
//			model.addAttribute("categoryList", pageCategory.getContent());
		}
		 else {

			Page<Category> pageCategory = categoryRepo.findAll(pageable);

			model.addAttribute("totalPage", pageCategory.getTotalPages());
			model.addAttribute("categoryList", pageCategory.getContent());
		}

		model.addAttribute("name", name);
		return "category/search.html";
	}
	
	@GetMapping("/category/search")
	public String search(Model model) {
		model.addAttribute("categoryList", categoryRepo.findAll());
		return "/category/search";
	}
	
	@GetMapping("/category/delete")
	public String delete(@RequestParam("id") int id) {
		categoryRepo.deleteById(id);
		return "redirect:/category/search";
	}
	
	@GetMapping("/category/edit")
	public String edit( @RequestParam("id") int id, Model model) {
		
		model.addAttribute("category", categoryRepo.findById(id).orElse(null));
		
		return "/category/edit.html";
	}
	
	@PostMapping("/category/edit")
	public String edit( @ModelAttribute Category category) {
		
		Category category2 = categoryRepo.findById(category.getId()).orElse(null);
		
		category2.setName(category.getName());
		categoryRepo.save(category2);
		
		return "redirect:/category/search";
	}
	
}
