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

import com.ducnt.project2.dto.Student;
import com.ducnt.project2.repo.StudentRepo;

@Controller
public class StudentController {

	@Autowired
	StudentRepo studentRepo;

	@GetMapping("/student/create")
	public String create() {
		return "student/create.html";
	}

	@PostMapping("/student/create")
	public String create(@ModelAttribute Student student) {

		studentRepo.save(student);
		return "redirect:/student/search";
	}

	@GetMapping("/student/search")
	public String search(Model model) {

		model.addAttribute("studentList", studentRepo.findAll());
		return "/student/search.html";
	}

	

	@GetMapping("/student/delete")
	public String delete(@RequestParam("studentId") int studentId) {

		studentRepo.deleteById(studentId);

		return "redirect:/student/search";
	}
	
	@GetMapping("/student/edit")
	public String edit(Model model, @RequestParam("studentId") int studentId) {

		model.addAttribute("student", studentRepo.findById(studentId).orElse(null));

		return "student/edit.html";
	}

	@PostMapping("/student/edit")
	public String edit(@ModelAttribute("student") @Validated Student student, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/student/edit.html";
		}
		studentRepo.save(student);
		return "redirect:/student/search";
	}

}
