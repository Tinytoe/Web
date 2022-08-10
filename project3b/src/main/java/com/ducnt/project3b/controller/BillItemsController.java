package com.ducnt.project3b.controller;

import com.ducnt.project3b.dto.ResponseDTO;
import com.ducnt.project3b.dto.SearchDTO;
import com.ducnt.project3b.dto.billitems.BillItemsDTO;
import com.ducnt.project3b.entity.billItems.BillItems;
import com.ducnt.project3b.repo.BillItemsRepo;
import com.ducnt.project3b.service.BillItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/bill-items/")
public class BillItemsController {

    @Autowired
    BillItemsService billItemsService;

    @PostMapping("/create")
    public ResponseDTO<Integer> create(@ModelAttribute BillItemsDTO billItemsDTO){
        int id = billItemsService.create(billItemsDTO);
        return ResponseDTO.<Integer>builder().code(HttpStatus.OK.value()).data(id).build();
    }

    @GetMapping("/getOne/{id}")
    public ResponseDTO<BillItemsDTO> getOne(@PathVariable("id") int id){
        return ResponseDTO.<BillItemsDTO>builder().code(HttpStatus.OK.value()).data(billItemsService.getOne(id)).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDTO<Void> delete(@PathVariable("id") int id){
        billItemsService.delete(id);
        return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
    }
    @PutMapping("/update")
    public ResponseDTO<Void> update(@ModelAttribute(name = "billItems") @Validated BillItemsDTO billItemsDTO){
        billItemsService.update(billItemsDTO);
        return ResponseDTO.<Void>builder().code(HttpStatus.OK.value()).build();
    }

    @PostMapping("/search")
    public ResponseDTO<List<BillItemsDTO>> search(@RequestBody SearchDTO searchDTO){

        return billItemsService.search(searchDTO);
    }
}
