package com.ersin.retrofitDemo.business.requests.product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
	private String description;
	@NotNull
	@NotBlank
	private int id;
	private String image;
	private Double price;
	@NotNull
	@NotBlank
	@Size(min = 4, max = 20)
	private String title;
	private Integer categoryID;
}