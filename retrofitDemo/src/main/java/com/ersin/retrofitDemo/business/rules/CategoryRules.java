package com.ersin.retrofitDemo.business.rules;

import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.core.utilities.exceptions.BusinessException;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoryRules {
	private CategoryRepository categoryRepository;

	public void checkifCategoryNameExists(String name) {
		if (this.categoryRepository.existsByName(name)) {
			throw new BusinessException("Category name already exists ");
		}
	}
}
