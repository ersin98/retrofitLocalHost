package com.ersin.retrofitDemo.business.requests.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProducRequestController {
	private Boolean suitable;
	private Boolean done;

	private String description;
	private String imageData;
	private String price;
	private String title;
}