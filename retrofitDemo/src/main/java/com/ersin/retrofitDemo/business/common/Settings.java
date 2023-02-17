package com.ersin.retrofitDemo.business.common;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Settings {
	// ---------------------Produck------------------//
	// Tekrar etme
	private Boolean imageRepeatErrorCheck = false;
	private Boolean descriptionRepeatErrorCheck = false;
	private Boolean titleRepeatErrorCheck = true;
	// Boş olma
	private Boolean imageEmptyErrorCheck = true;
	private Boolean descriptionEmptyErrorCheck = true;
	private Boolean titleEmptyErrorCheck = true;
	private Boolean priceEmptyErrorCheck = true;
	// Veri Türü
	private Boolean pricedataTypeErrorCheck = true;

	// -------------------Category---------------//
	private Boolean nameRepeatErrorCheck = true;
	private Boolean nameEmtyErrorCheck = true;
}
