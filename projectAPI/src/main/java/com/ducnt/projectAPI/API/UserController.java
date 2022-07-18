//package com.ducnt.projectAPI.API;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//import com.ducnt.projectAPI.entity.User;
//import com.ducnt.projectAPI.repo.UserRepo;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//	
//	@Autowired 
//	UserRepo userRepo;
//	
//	@PostMapping("/create")
//	public User create(@RequestBody @Validated User user) {
//		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//		userRepo.save(user);
//		return user;
//	}
//	
//	@DeleteMapping("/delete")
//	public void delete(@RequestParam("id") int id) {
//		userRepo.deleteById(id);
//	}
//	
//	@PutMapping("/update")
//	public User update(@RequestBody @Validated User user) {
//		userRepo.save(user);
//		return user;
//	}
//	
//	@GetMapping("/get")
//	public User getOne(@RequestParam(name = "id") int id) {
//		return userRepo.findById(id).orElse(null);
//	}
//	
//	@GetMapping("/getAll")
//	public List<User> getAll(){
//		return userRepo.findAll();
//	}
//}
