//package com.ducnt.projectAPI.API;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ducnt.projectAPI.entity.Category;
//import com.ducnt.projectAPI.entity.Product;
//import com.ducnt.projectAPI.repo.CategoryRepo;
//import com.ducnt.projectAPI.repo.ProductRepo;
//
//@RestController
//@RequestMapping("/api/product")
//public class ProductController {
//	
//	@Autowired 
//	ProductRepo productRepo;
//	
//	@Autowired
//	CategoryRepo categoryRepo;
//	
//	@PostMapping("/create")
//	public Product create(@ModelAttribute Product product,
//						   @RequestParam(name = "categoryId") int categoryId) {
//		
//		Category category = categoryRepo.findById(categoryId).orElse(null);
//		category.setId(categoryId);
//		product.setCategory(category);
//		
//		productRepo.save(product);
//		
//		return product;
//	}
//	
//	@DeleteMapping("/delete")
//	public void delete(@RequestParam("id") int id) {
//		productRepo.deleteById(id);
//	}
//	
////	@PutMapping("/update")
////	public Bill update( @RequestBody @Validated Bill bill
////						) {
////		
////		Bill bill2 = billRepo.findById(id).orElse(null);
////		User user2 = bill2.getUser();
////		
////		
////		user2.setId(userId);
////		bill2.setUser(user2);
////		billRepo.save(bill2);
////		
////		return bill2;
////	}
//	@GetMapping("/get")
//	public Product getOne(@RequestParam(name = "id") int id) {
//		return productRepo.findById(id).orElse(null);
//	}
//	
//	@GetMapping("/getAll")
//	public List<Product> getAll(){
//		return productRepo.findAll();
//	}
//	
//}
