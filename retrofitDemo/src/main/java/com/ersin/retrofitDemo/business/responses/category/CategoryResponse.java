package com.ersin.retrofitDemo.business.responses.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
	private Boolean suitable;
	private Boolean done;

	private String errorMassage;
}
