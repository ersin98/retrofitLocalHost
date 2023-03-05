package com.ersin.retrofitDemo.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.CategoryService;
import com.ersin.retrofitDemo.business.core.utilities.mappers.ModelMapperService;
import com.ersin.retrofitDemo.business.requests.category.CreateCategoryRequest;
import com.ersin.retrofitDemo.business.requests.category.UpdateCategoryRequest;
import com.ersin.retrofitDemo.business.responses.category.GetAllCategoryResponse;
import com.ersin.retrofitDemo.business.rules.CategoryRules;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;
import com.ersin.retrofitDemo.entities.concretes.Category;

import lombok.AllArgsConstructor;

//@Slf4j
@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
	private CategoryRepository categoryRepository;
	private ModelMapperService mapperService;
	private CategoryRules categoryRules;

	@Override
	public List<GetAllCategoryResponse> getAll() {
		// burada findAll dan GetAll içine aktarma yapıyoruz
		List<Category> categories = this.categoryRepository.findAll();
		List<GetAllCategoryResponse> categoryResponse = categories.stream()
				.map(category -> this.mapperService.forResponse().map(category, GetAllCategoryResponse.class))
				.collect(Collectors.toList());
		return categoryResponse;
	}

	@Override
	public void addCategory(CreateCategoryRequest createcategoryRequest) {
		this.categoryRules.checkifCategoryNameExists(createcategoryRequest.getName());
		Category category = this.mapperService.forRequest().map(createcategoryRequest, Category.class);
		this.categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int id) { // Otomatik silmek yerine kullanıcıya product var hatası vermeli
		Category category = this.categoryRepository.findById(id).orElseThrow();
		this.categoryRepository.delete(category);
	}

	@Override
	public void deleteAll() {
		this.categoryRepository.deleteAll();
	}

	@Override
	public void updateCategoryRequest(UpdateCategoryRequest updateCategoryRequest) {
		this.categoryRepository.findById(updateCategoryRequest.getId()).orElseThrow();
		Category categoryRequest = this.mapperService.forRequest().map(updateCategoryRequest, Category.class);
		this.categoryRepository.save(categoryRequest);
	}
}
