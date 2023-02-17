package com.ersin.retrofitDemo.business.responses.product;

import com.ersin.retrofitDemo.entities.concretes.Categories;

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
	private Categories categories;
}