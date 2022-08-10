package com.ducnt.project3b.controller;

import com.ducnt.project3b.dto.ResponseDTO;
import com.ducnt.project3b.dto.coupon.CouponDTO;
import com.ducnt.project3b.dto.user.SearchUserDTO;
import com.ducnt.project3b.dto.user.UserDTO;
import com.ducnt.project3b.service.CouponService;
import com.ducnt.project3b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api/admin/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public ResponseDTO<Integer> create(@ModelAttribute CouponDTO couponDTO) throws ParseException {

        int id = couponService.create(couponDTO);
        return ResponseDTO.<Integer>builder().code(HttpStatus.OK.value()).data(id).build();
    }

//	@PostMapping("/search")
//	public ResponseDTO<List<UserDTO>> search(@RequestBody SearchUserDTO searchUserDTO){
//		return userService.search(searchUserDTO);
//	}

//    @DeleteMapping("/delete/{id}")
//    public ResponseDTO<Void> delete(@PathVariable(name = "id") int id) {
//        userService.delete(id);
//        return  ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
//    }
//
//
//
//    @GetMapping("/getOne/{id}")
//    public ResponseDTO<UserDTO> getOne(@PathVariable(name = "id") int id) {
//        return ResponseDTO.<UserDTO>builder().code(HttpStatus.OK.value()).data(userService.getOne(id)).build();
//    }
//
//    @PutMapping("/update")
//    public ResponseDTO<Void> update(@ModelAttribute(name = "user") @Validated UserDTO userDTO) throws ParseException {
//
//        if (userDTO.getFile() != null && userDTO.getFile().getSize() > 0){
//            // co thi luu lai vao folder
//            final String FOLDER = "E:\\UserAvatar\\";
//
//            String filename = userDTO.getFile().getOriginalFilename();
//
//            File outputFile = new File(FOLDER + filename);
//
//            try {
//                userDTO.getFile().transferTo(outputFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            //api downloadfile
//            userDTO.setAvatar("/api/user/download?filename=" + filename);
//        }
//
//        userDTO.setPassword(userDTO.getPassword());
//        userService.update(userDTO);
//
//        return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
//    }
//
//    @PostMapping("/reset-password")
//    public ResponseDTO<Void> resetPassword(@RequestParam(name = "email") String email) throws MessagingException {
//        return  userService.resetPassword(email);
//    }
}
