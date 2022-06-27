package com.ducnt.project2.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ducnt.project2.dto.User;
import com.ducnt.project2.repo.UserRepo;
import com.ducnt.project2.repo.UserRoleRepo;

@Controller
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserRoleRepo userRoleRepo;

	@GetMapping("/user/create")
	public String create(Model model) {
		
	model.addAttribute("userRoleList", userRoleRepo.findAll());
		return "user/create.html";
	}

	@PostMapping("/user/create")
	public String createUser(@ModelAttribute User user,
							 @RequestParam(name = "file", required = false) MultipartFile file,
							 @RequestParam(name = "bDate") String bDate) throws IllegalStateException, IOException, ParseException {

		if (file != null && file.getSize() > 0) {

			final String FOLDER = "E:/";

			String filename = file.getOriginalFilename();

			File outputFile = new File(FOLDER + filename);

			file.transferTo(outputFile);

			user.setAvatar("/user/avatar?filename=" + filename);
		}
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		user.setBirthdate(sdf.parse(bDate));

		userRepo.save(user);
		return "redirect:/user/search";
	}


	@GetMapping("/user/search")
	public String search(Model model) {

		model.addAttribute("userList", userRepo.findAll());
		return "/user/search.html";
	}

	
	@GetMapping("/user/delete")
	public String delete(@RequestParam("id") int id) {

		userRepo.deleteById(id);

		return "redirect:/user/search";
	}

	@GetMapping("/user/edit")
	public String edit(Model model, @RequestParam("id") int id) {
				
		model.addAttribute("user", userRepo.findById(id).orElse(null));

		return "user/edit.html";
	}
	@PostMapping("/user/edit")
	public String edit(@ModelAttribute("user") @Validated User user, 
						BindingResult bindingResult
						) {
		
		userRepo.save(user);
		return "redirect:/user/search";
	}

	@GetMapping("/change-lang")
	public void changeLang(HttpServletResponse resp, @RequestHeader("Referer") String refer) throws IOException {
		System.out.println(refer);
		resp.sendRedirect(refer);
	}
}
