package com.ducnt.project3b.entity.billItems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ducnt.project3b.entity.product.Product;
import com.ducnt.project3b.entity.bill.Bill;
import lombok.Data;

@Entity
@Table
@Data
public class BillItems {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "bill_id")
    Bill bill;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	Product product;
	
	private int quantity;
	
	private double buyPrice;

}
