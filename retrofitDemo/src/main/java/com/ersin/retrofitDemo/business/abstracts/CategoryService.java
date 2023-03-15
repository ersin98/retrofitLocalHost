package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.category.CreateCategoryRequest;
import com.ersin.retrofitDemo.business.requests.category.UpdateCategoryRequest;
import com.ersin.retrofitDemo.business.responses.category.GetAllCategoryResponse;

public interface CategoryService {

	List<GetAllCategoryResponse> getAll();

	void addCategory(CreateCategoryRequest createcategoryRequest);

	void deleteCategory(int id);

	void deleteAll();

	void updateCategoryRequest(UpdateCategoryRequest updateCategoryRequest);

}
