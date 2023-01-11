package com.ersin.retrofitDemo.business.concretes;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Settings {
	private Boolean imageRepeatErrorCheck = false;
	private Boolean descriptionRepeatErrorCheck = false;
	private Boolean titleRepeatErrorCheck = true;

	private Boolean imageEmptyErrorCheck = true;
	private Boolean descriptionEmptyErrorCheck = true;
	private Boolean titleEmptyErrorCheck = true;
	private Boolean priceEmptyErrorCheck = true;

	private Boolean pricedataTypeErrorCheck = true;
}
