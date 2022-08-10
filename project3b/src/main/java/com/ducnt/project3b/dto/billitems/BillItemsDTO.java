package com.ducnt.project3b.dto.billitems;

import com.ducnt.project3b.entity.bill.Bill;
import com.ducnt.project3b.entity.product.Product;
import lombok.Data;

@Data
public class BillItemsDTO {
	
	private int id;
	
    Bill bill;
	
    Product product;
	
	private int quantity;
	
	private double buyPrice;
	
	
}
