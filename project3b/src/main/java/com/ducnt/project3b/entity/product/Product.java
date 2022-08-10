package com.ducnt.project3b.entity.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.ducnt.project3b.entity.category.Category;
import lombok.Data;

@Entity
@Table
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Min(0)
	private double price;
	
	private String description;
	
	private String urlImage;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
    Category category;

}
