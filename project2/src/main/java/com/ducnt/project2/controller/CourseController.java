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

import com.ducnt.project2.dto.Course;
import com.ducnt.project2.repo.CourseRepo;
import com.ducnt.project2.repo.ScoreRepo;

@Controller
public class CourseController {

	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	ScoreRepo scoreRepo;

	@GetMapping("/course/create")
	public String create() {
		return "course/create.html";
	}

	@PostMapping("/course/create")
	public String create(@ModelAttribute Course course) {

		courseRepo.save(course);
		return "redirect:/course/search";
	}

	@GetMapping("/course/search")
	public String search(Model model) {

		model.addAttribute("courseList", courseRepo.findAll());
		return "/course/search.html";
	}

	

	@GetMapping("/course/delete")
	public String delete(@RequestParam("id") int id) {

		courseRepo.deleteById(id);

		return "redirect:/course/search";
	}
	
	@GetMapping("/course/edit")
	public String edit(Model model, @RequestParam("id") int id) {

		model.addAttribute("course", courseRepo.findById(id).orElse(null));

		return "course/edit.html";
	}

	@PostMapping("/course/edit")
	public String edit(@ModelAttribute("score") @Validated Course course, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "/course/edit.html";

		}
		courseRepo.save(course);
		return "redirect:/course/search";
	}

}
