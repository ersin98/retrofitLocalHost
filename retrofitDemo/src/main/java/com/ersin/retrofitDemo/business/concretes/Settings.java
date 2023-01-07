package com.ersin.retrofitDemo.business.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Settings {
	private Boolean imageDataCanRepeat;
	private Boolean descriptionCanRepeat;
	private Boolean priceCanRepeat;
	private Boolean titleCanRepeat;
}