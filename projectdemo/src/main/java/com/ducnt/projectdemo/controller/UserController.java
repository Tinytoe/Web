package com.ducnt.projectdemo.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ducnt.projectdemo.entity.User;
import com.ducnt.projectdemo.model.MessageDTO;
import com.ducnt.projectdemo.repo.UserRepo;
import com.ducnt.projectdemo.service.MailService;

@Controller
public class UserController {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	MailService mailService;

	@GetMapping("/user/create")
	public String create(Model model) {
		return "user/create.html";
	}

	@PostMapping("/user/create")
	public String createUser(@ModelAttribute User user, HttpSession session, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		model.addAttribute("success", session.getAttribute("success"));
		return "redirect:/home";
	}

	@PostMapping("/user/search")
	public String search(Model model, @RequestParam(name = "size", required = false) String size,
			@RequestParam(name = "page", required = false) String page,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "username", required = false) String username) {

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
			Page<User> pageUser = userRepo.searchByName("%" + name + "%", pageable);
			model.addAttribute("totalPage", pageUser.getTotalPages());
			model.addAttribute("userList", pageUser.getContent());
		}
		if (StringUtils.hasText(username)) {
			Page<User> pageUser = userRepo.searchByUserName("%" + username + "%", pageable);
			model.addAttribute("totalPage", pageUser.getTotalPages());
			model.addAttribute("userList", pageUser.getContent());
		} else {

			Page<User> pageUser = userRepo.findAll(pageable);

			model.addAttribute("totalPage", pageUser.getTotalPages());
			model.addAttribute("userList", pageUser.getContent());
		}

		model.addAttribute("name", name);
		return "user/search.html";
	}

	@GetMapping("/user/search")
	public String search(Model model) {
		model.addAttribute("userList", userRepo.findAll());
		return "/user/search.html";
	}

	@GetMapping("/user/getOne/{id}")
//	@PostAuthorize("#returnObject.username == authentication.principal.username"
//			+ " or hasRole(ROLE_ADMIN)")
	public User get(@PathVariable("id") int id) {

		return userRepo.findById(id).orElse(null);
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
	public String edit(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {

		userRepo.save(user);
		return "redirect:/user/search";
	}

	@GetMapping("/user/forget-password")
	public String forgetPass() {
		return "user/forget-password.html";
	}

	@PostMapping("/user/forget-password")
	public String forgetPass(@RequestParam(name = "email") String email, Model model) throws Exception {
		
		User user = userRepo.findByEmail(email);	
		MessageDTO messageDTO = new MessageDTO();
		
		if (StringUtils.hasText(email)) {
			
			if (user != null) {
				String newPassword = RandomStringUtils.random(6,"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(newPassword));
				messageDTO.setContent(newPassword);
				userRepo.save(user);
				
				messageDTO.setToName(user.getName());
				messageDTO.setTo(user.getEmail());
				
				mailService.sendEmailPassword(messageDTO);
				
			} else {
				throw new Exception("CANT FIND USER WITH EMAIL: " + email) ;
			}
			

		} else new Exception("Enter Your Email");
		
		model.addAttribute("u", user);
		
		return "user/change-password-success.html";

	}
}
