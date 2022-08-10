package com.ducnt.project3b.service;

import com.ducnt.project3b.dto.ResponseDTO;
import com.ducnt.project3b.dto.SearchDTO;
import com.ducnt.project3b.dto.bill.BillDTO;
import com.ducnt.project3b.dto.billitems.BillItemsDTO;
import com.ducnt.project3b.entity.bill.Bill;
import com.ducnt.project3b.entity.billItems.BillItems;
import com.ducnt.project3b.entity.product.Product;
import com.ducnt.project3b.repo.BillItemsRepo;
import com.ducnt.project3b.repo.BillRepo;
import com.ducnt.project3b.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface BillItemsService {

    int create(BillItemsDTO billItemsDTO);

    void update(BillItemsDTO billItemsDTO);

    void delete(int id);

    BillItemsDTO getOne(int id);

    ResponseDTO<List<BillItemsDTO>> search(SearchDTO searchDTO);

    @Service
    @Transactional
    class BillServiceImpl implements BillItemsService {

        @Autowired
        BillItemsRepo billItemsRepo;

        @Autowired
        ProductRepo productRepo;

        @Override
        public int create(BillItemsDTO billItemsDTO) {
            BillItems billItems = new ModelMapper().createTypeMap(BillItemsDTO.class, BillItems.class).map(billItemsDTO);
            Product product = productRepo.findById(billItemsDTO.getProduct().getId()).orElseThrow(NoResultException::new);
            billItems.setBuyPrice(product.getPrice());
            billItemsRepo.save(billItems);
            return billItems.getId();
        }

        @Override
        public void update(BillItemsDTO billItemsDTO) {
            BillItems newBillItems = new ModelMapper().map(BillItemsDTO.class, BillItems.class);
            billItemsRepo.save(newBillItems);
        }

        @Override
        public void delete(int id) {
            billItemsRepo.deleteById(id);
        }

        @Override
        public BillItemsDTO getOne(int id) {
            BillItems billItems = billItemsRepo.findById(id).orElseThrow(NoResultException::new);
            BillItemsDTO billItemsDTO = new ModelMapper().createTypeMap(billItems, BillItemsDTO.class).map(billItems);
            return billItemsDTO;
        }

        @Override
        public ResponseDTO<List<BillItemsDTO>> search(SearchDTO searchDTO) {
            Pageable pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize());
            Page<BillItems> pageBillItems = billItemsRepo.findAll(pageable);

            ResponseDTO<List<BillItemsDTO>> resp = new ResponseDTO<List<BillItemsDTO>>();
            resp.setCode(HttpStatus.OK.value());
            resp.setTotalPage(pageBillItems.getTotalPages());

            List<BillItemsDTO> billItemsDTOS = new ArrayList<BillItemsDTO>();
            for (BillItems billItems : pageBillItems.getContent()){

                BillItemsDTO billItemsDTO = new ModelMapper().map(billItems, BillItemsDTO.class);
                billItemsDTOS.add(billItemsDTO);

            }

            resp.setData(billItemsDTOS);
            return resp;
        }

    }
}
