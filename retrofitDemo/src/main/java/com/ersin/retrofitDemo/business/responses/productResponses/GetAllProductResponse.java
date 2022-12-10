package com.ersin.retrofitDemo.business.responses.productResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductResponse {
	private String description;
	private int id;
	private int imageId;
	private Double price;
	private String title;
}
