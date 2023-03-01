package com.ersin.retrofitDemo.business.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	private Boolean suitable;
	private Boolean done;

	private String errorMassage;
}
