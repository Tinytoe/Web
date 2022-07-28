package com.ducnt.projectdemo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "{not.empty}")
	private String name;
	
	private double price;
	
	private String description;
	
	private String urlImage;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	Category category;
	
	
	
}
