package com.ducnt.project3b.dto.product;

import com.ducnt.project3b.entity.category.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ProductDTO {

	private int id;

	@NotEmpty(message = "{not.empty}")
	private String name;

	@Min(0)
	private double price;

	private String description;

	private String urlImage;

	@JsonIgnore
	private MultipartFile file;

	private Category category;

}
