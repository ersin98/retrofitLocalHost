package com.ersin.retrofitDemo.business.responses.category;

import java.util.List;

import com.ersin.retrofitDemo.entities.concretes.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoryResponse {
	private int id;
	private String name;
	private List<Products> product;
}
