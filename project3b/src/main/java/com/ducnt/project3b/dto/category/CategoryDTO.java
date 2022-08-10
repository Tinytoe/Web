package com.ducnt.project3b.dto.category;

import java.util.List;

import com.ducnt.project3b.entity.category.Category;
import com.ducnt.project3b.entity.product.Product;

import lombok.Data;

@Data
public class CategoryDTO {
	
	private int id;
	
	private String name;

	private Category parentCategory;
	
}
