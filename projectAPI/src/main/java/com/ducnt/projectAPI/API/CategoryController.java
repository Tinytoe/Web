//package com.ducnt.projectAPI.API;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ducnt.projectAPI.entity.Category;
//import com.ducnt.projectAPI.repo.CategoryRepo;
//
//@RestController
//@RequestMapping("/api/category")
//public class CategoryController {
//	
//	@Autowired 
//	CategoryRepo categoryRepo;
//	
//	@PostMapping("/create")
//	public Category create(@RequestBody @Validated Category category) {
//		categoryRepo.save(category);
//		return category;
//	}
//	
//	@DeleteMapping("/delete")
//	public void delete(@RequestParam("id") int id) {
//		categoryRepo.deleteById(id);
//	}
//	
//	@PutMapping("/update")
//	public Category update(@RequestParam(name = "id") int id,
//						   @Validated Category category) {
//		categoryRepo.save(category);
//		return category;
//	}
//	@GetMapping("/get")
//	public Category getOne(@RequestParam(name = "id") int id) {
//		return categoryRepo.findById(id).orElse(null);
//	}
//	
//	@GetMapping("/getAll")
//	public List<Category> getAll(){
//		return categoryRepo.findAll();
//	}
//	
//}
