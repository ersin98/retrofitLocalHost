package com.ersin.retrofitDemo.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByQueryProductResponse {
	private String description;
	private String image;
	private Double price;
	private String title;
	int id;
	private Integer categoryID;
}