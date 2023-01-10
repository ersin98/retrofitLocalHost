package com.ersin.retrofitDemo.business.concretes;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Settings {
	private Boolean imageCantRepeat = false;
	private Boolean descriptionCantRepeat = false;
	private Boolean titleCantRepeat = true;

	private Boolean imageCantEmpty = true;
	private Boolean descriptionCantEmpty = true;
	private Boolean titleCantEmpty = true;
	private Boolean priceCantEmpty = true;
}
