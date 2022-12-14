package com.ersin.retrofitDemo.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByQueryProductResponse {
	private String description;
	private String imageData;
	private Double price;
	private String title;
}
