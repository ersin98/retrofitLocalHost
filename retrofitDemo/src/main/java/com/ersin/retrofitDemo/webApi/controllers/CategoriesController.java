package com.ersin.retrofitDemo.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersin.retrofitDemo.business.abstracts.CategoryService;
import com.ersin.retrofitDemo.business.requests.category.CreateCategoryRequest;
import com.ersin.retrofitDemo.business.requests.category.UpdateCategoryRequest;
import com.ersin.retrofitDemo.business.responses.category.CategoryResponse;
import com.ersin.retrofitDemo.business.responses.category.GetAllCategoryResponse;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
	private CategoryService categortyservice;

	public CategoriesController(CategoryService categortyservice) {
		super();
		this.categortyservice = categortyservice;
	}

	@GetMapping("/getall")
	List<GetAllCategoryResponse> getAll() {
		return categortyservice.getAll();
	}

	@PostMapping("/add")
	public CategoryResponse addCategory(@RequestBody CreateCategoryRequest createcategoryRequest) {
		return categortyservice.addCategory(createcategoryRequest);
	}

	@PostMapping("/delete")
	public void deleteCategory(int id) {
		categortyservice.deleteCategory(id);
	}

	@PostMapping("/deleteall")
	public void deleteAll() {
		categortyservice.deleteAll();
	}

	@PostMapping("/update")
	public CategoryResponse updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
		return categortyservice.updateCategoryRequest(updateCategoryRequest);
	}
}
