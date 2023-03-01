package com.ersin.retrofitDemo.business.requests.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryProductResponse {
	private String description;
	private int id;
	private String image;
	private Double price;
	private String title;
	private Integer categoryID;
}
