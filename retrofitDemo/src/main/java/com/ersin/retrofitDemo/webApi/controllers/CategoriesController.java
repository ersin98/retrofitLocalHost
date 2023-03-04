package com.ersin.retrofitDemo.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersin.retrofitDemo.business.abstracts.CategoryService;
import com.ersin.retrofitDemo.business.requests.category.CreateCategoryRequest;
import com.ersin.retrofitDemo.business.requests.category.UpdateCategoryRequest;
import com.ersin.retrofitDemo.business.responses.category.GetAllCategoryResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
	private CategoryService categortyservice;

	@GetMapping()
	List<GetAllCategoryResponse> getAll() {
		return categortyservice.getAll();
	}

	@PostMapping()
	public void addCategory(@RequestBody @Valid CreateCategoryRequest createcategoryRequest) {
		categortyservice.addCategory(createcategoryRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteCategory(int id) {
		categortyservice.deleteCategory(id);
	}

	@DeleteMapping()
	public void deleteAll() {
		categortyservice.deleteAll();
	}

	@PutMapping()
	public void updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {
		categortyservice.updateCategoryRequest(updateCategoryRequest);
	}
}
