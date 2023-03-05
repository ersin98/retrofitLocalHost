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
public class CreateProductRequest {
	@NotNull
	@NotBlank
	private String description;
	@NotNull
	@NotBlank
	private String image;
	@NotNull
	@NotBlank
	private Double price;
	@NotNull
	@NotBlank
	@Size(min = 4, max = 20)
	private String title;
	@NotNull
	@NotBlank
	private Integer categoryID;
	// @NotBlank @NotEmpty
}