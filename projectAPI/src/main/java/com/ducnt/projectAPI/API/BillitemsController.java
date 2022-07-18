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
//import com.ducnt.projectAPI.entity.Bill;
//import com.ducnt.projectAPI.entity.BillItems;
//import com.ducnt.projectAPI.entity.Product;
//import com.ducnt.projectAPI.repo.BillItemsRepo;
//import com.ducnt.projectAPI.repo.BillRepo;
//import com.ducnt.projectAPI.repo.ProductRepo;
//
//@RestController
//@RequestMapping("/api/billItems")
//public class BillitemsController {
//	
//	@Autowired 
//	BillRepo billRepo;
//	
//	@Autowired
//	BillItemsRepo billItemsRepo;
//	
//	@Autowired
//	ProductRepo productRepo;
//	
//	@PostMapping("/create")
//	public BillItems createBillItems(@ModelAttribute BillItems billItems,
//						   @RequestParam(name = "billId") int billId,
//						   @RequestParam(name = "productId") int productId) {
//		
//		Bill bill2 = billRepo.findById(billId).orElse(null);
//		bill2.setId(billId);
//		billItems.setBill(bill2);
//		
//		Product product2 = productRepo.findById(productId).orElse(null);
//		product2.setId(productId);
//		billItems.setProduct(product2);
//		billItems.setBuyPrice(product2.getPrice());
//		
//		billItemsRepo.save(billItems);
//		
//		return billItems;
//	}
//	@DeleteMapping("/delete")
//	public void delete(@RequestParam("id") int id) {
//		billItemsRepo.deleteById(id);
//	}
//	
////	@PutMapping("/bill/update")
////	public Bill update( @Validated Bill bill,
////						@RequestParam(name = "id") int id,
////						@RequestParam(name = "userId") int userId) {
////		
////		Bill bill2 = billRepo.findById(id).orElse(null);
////		User user2 = bill2.getUser();
////		
////		
////		user2.setId(userId);
////		bill2.setUser(user2);
////		System.out.println(bill2.getUser().getId());
////		billRepo.save(bill2);
////		
////		return bill2;
////	}
//	@GetMapping("/get")
//	public BillItems getOne(@RequestParam(name = "id") int id) {
//		return billItemsRepo.findById(id).orElse(null);
//	}
//	
//	@GetMapping("/getAll")
//	public List<BillItems> getAll(){
//		return billItemsRepo.findAll();
//	}
//}
