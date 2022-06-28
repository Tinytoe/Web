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

import com.ducnt.project2.dto.Score;
import com.ducnt.project2.repo.CourseRepo;
import com.ducnt.project2.repo.ScoreRepo;
import com.ducnt.project2.repo.StudentRepo;

@Controller
public class ScoreController {

	@Autowired
	ScoreRepo scoreRepo;
	
	@Autowired
	CourseRepo courseRepo;
	
	@Autowired
	StudentRepo studentRepo;

	@GetMapping("/score/create")
	public String create(Model model) {
		
		model.addAttribute("studentList", studentRepo.findAll());
		model.addAttribute("coursetList", courseRepo.findAll());
		return "score/create.html";
		
	}

	@PostMapping("/score/create")
	public String create(@ModelAttribute Score score) {

		scoreRepo.save(score);
		return "redirect:/score/search";
	}

	@GetMapping("/score/search")
	public String search(Model model) {

		model.addAttribute("scoreList", scoreRepo.findAll());
		return "/score/search.html";
	}

	

	@GetMapping("/score/delete")
	public String delete(@RequestParam("id") int id) {

		scoreRepo.deleteById(id);

		return "redirect:/score/search";
	}
	
	@GetMapping("/score/edit")
	public String edit(Model model, @RequestParam("id") int id) {

		model.addAttribute("score", scoreRepo.findById(id).orElse(null));

		return "score/edit.html";
	}

	@PostMapping("/score/edit")
	public String edit(@ModelAttribute("score") @Validated Score score, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return "/score/edit.html";

		}
		scoreRepo.save(score);
		return "redirect:/score/search";
	}

}
