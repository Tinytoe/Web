package com.ducnt.projectAPI.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartFile;

import com.ducnt.projectAPI.entity.Product;
import com.ducnt.projectAPI.repo.CategoryRepo;
import com.ducnt.projectAPI.repo.ProductRepo;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@GetMapping("/product/create")
	public String create(Model model) {
		model.addAttribute("categoryList", categoryRepo.findAll());
		return "product/create.html";
	}
	
	@PostMapping("/product/create")
	public String create(@ModelAttribute Product product,
						 @RequestParam(name = "file", required = false) MultipartFile file) {
		
		if (file != null && file.getSize() > 0) {

			final String FOLDER = "E:\\UpLoadFile\\";

			String filename = file.getOriginalFilename();

			File outputFile = new File(FOLDER + filename);

			try {
				file.transferTo(outputFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			product.setUrlImage(filename);
		}
		productRepo.save(product);
		return "redirect:/product/search";
	}
	
	@GetMapping("/product/download-file")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String filename = request.getParameter("image");
		String dataDirectory = "E:\\UpLoadFile\\";
		Path file = Paths.get(dataDirectory, filename);
		if (Files.exists(file)) {
			response.setContentType("image/jpeg");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@PostMapping("/product/search")
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
			Page<Product> pageProduct = productRepo.searchByName("%" + name + "%", pageable);
			model.addAttribute("totalPage", pageProduct.getTotalPages());
			model.addAttribute("productList", pageProduct.getContent());
		}
		 else {

			Page<Product> pageProduct = productRepo.findAll(pageable);

			model.addAttribute("totalPage", pageProduct.getTotalPages());
			model.addAttribute("productList", pageProduct.getContent());
		}

		model.addAttribute("name", name);
		return "product/search.html";
	}
	
	@GetMapping("/product/search")
	public String search(Model model) {
		model.addAttribute("productList", productRepo.findAll());
		return "/product/search";
	}
	
	@GetMapping("/product/delete")
	public String delete(@RequestParam("id") int id) {
		productRepo.deleteById(id);
		return "redirect:/product/search";
	}
	
	@GetMapping("/product/edit")
	public String edit( @RequestParam("id") int id, Model model) {
		
		model.addAttribute("category", productRepo.findById(id).orElse(null));
		
		return "/product/edit.html";
	}
	
	@PostMapping("/product/edit")
	public String edit( @ModelAttribute Product product) {
		
		Product product2 = productRepo.findById(product.getId()).orElse(null);
		
		product2.setName(product.getName());
		productRepo.save(product2);
		
		return "redirect:/product/search";
	}
	
}
