package com.ersin.retrofitDemo.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {
	private String description;
	private String imageData;
	private Double price;
	private String title;
}
