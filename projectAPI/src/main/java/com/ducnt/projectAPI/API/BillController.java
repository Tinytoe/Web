//package com.ducnt.projectAPI.API;
//
//import java.util.Date;
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
//import com.ducnt.projectAPI.entity.User;
//import com.ducnt.projectAPI.repo.BillRepo;
//import com.ducnt.projectAPI.repo.UserRepo;
//
//@RestController
//@RequestMapping("/api/bill")
//public class BillController {
//	
//	@Autowired 
//	BillRepo billRepo;
//	
//	@Autowired
//	UserRepo userRepo;
//	
//	@PostMapping("/create")
//	public Bill create(@ModelAttribute Bill bill,
//					   @RequestParam(name = "userId") int userId) {
//		
//		User user2 = userRepo.findById(userId).orElse(null);
//		user2.setId(userId);
//		bill.setUser(user2);
//		bill.setBuyDate(new Date());
//		billRepo.save(bill);
//		
//		return bill;
//	}
//	
//	@DeleteMapping("/delete")
//	public void delete(@RequestParam("id") int id) {
//		billRepo.deleteById(id);
//	}
//	
////	@PutMapping("/update")
////	public Bill update(@RequestBody @Validated Bill bill
////						) {
////		
////		Bill bill2 = billRepo.findById(id).orElse(null);
////		User user2 = bill2.getUser();
////		
////		user2.setId(userId);
////		bill2.setUser(user2);
////		billRepo.save(bill2);
////		
////		return bill2;
////	}
//	@GetMapping("/get")
//	public Bill getOne(@RequestParam(name = "id") int id) {
//		return billRepo.findById(id).orElse(null);
//	}
//	
//	@GetMapping("/getAll")
//	public List<Bill> getAll(){
//		return billRepo.findAll();
//	}
//	
//}
