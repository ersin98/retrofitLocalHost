package com.ersin.retrofitDemo.business.rules;

import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.core.utilities.exceptions.BusinessException;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductRules {
	private ProductRepository productRepository;

	public void checkIfProductTitleExists(String name) {
		if (this.productRepository.existsByTitle(name)) {
			throw new BusinessException("Title already exists");
		}
	}

	public void checkIfProductImageExists(String name) {
		if (this.productRepository.existsByTitle(name)) {
			throw new BusinessException("Title already exists");
		}
	}
}