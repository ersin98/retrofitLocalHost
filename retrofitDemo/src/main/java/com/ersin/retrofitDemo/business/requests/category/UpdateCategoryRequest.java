package com.ersin.retrofitDemo.business.requests.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {
	@NotNull
	@NotBlank
	private int id;
	@NotNull
	@NotBlank
	private String name;
}
