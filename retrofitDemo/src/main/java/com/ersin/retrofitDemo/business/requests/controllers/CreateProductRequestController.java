package com.ersin.retrofitDemo.business.requests.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequestController {
	private Boolean suitable;
	private Boolean done;

	private String errorMassage;
}
