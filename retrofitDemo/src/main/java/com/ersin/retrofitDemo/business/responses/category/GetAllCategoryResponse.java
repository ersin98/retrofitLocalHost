package com.ersin.retrofitDemo.business.responses.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoryResponse {
	private int id;
	private String name;
}
