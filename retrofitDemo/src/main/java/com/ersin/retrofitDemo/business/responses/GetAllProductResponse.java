package com.ersin.retrofitDemo.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductResponse {
	private String description;
	private int id;
	private String imageData;
	private Double price;
	private String title;
}
