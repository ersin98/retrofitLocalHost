package com.ersin.retrofitDemo.business.requests.imageRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//sanırım buraya gerek yok
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateImageRequest {
	private int id;
	private String name;
	private String type;
	private byte[] imageData;
}
