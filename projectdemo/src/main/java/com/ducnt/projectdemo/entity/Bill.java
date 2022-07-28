package com.ducnt.projectdemo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date buyDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;
	
	@OneToMany(mappedBy = "bill")
	List<BillItems> billItems;
	
	private String couponCode;
	
	private double discount;
}
