package com.ersin.retrofitDemo.business.requests.product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
	@NotNull
	private String description;
	@NotNull
	private int id;
	@NotNull
	private String image;
	@NotNull
	private Double price;
	@NotNull
	@Size(min = 4, max = 20)
	private String title;
	@NotNull
	private int categoryID;
}