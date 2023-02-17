package com.ersin.retrofitDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.CategoryService;
import com.ersin.retrofitDemo.business.common.Settings;
import com.ersin.retrofitDemo.business.requests.category.CreateCategoryRequest;
import com.ersin.retrofitDemo.business.requests.category.UpdateCategoryRequest;
import com.ersin.retrofitDemo.business.responses.category.CategoryResponse;
import com.ersin.retrofitDemo.business.responses.category.GetAllCategoryResponse;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;
import com.ersin.retrofitDemo.entities.concretes.Categories;

@Service
public class CategoryManager implements CategoryService {
	private CategoryRepository categoryRepository;
	private Settings settings;

	public CategoryManager(CategoryRepository categoryRepository, Settings settings) {
		super();
		this.categoryRepository = categoryRepository;
		this.settings = settings;
	}

	@Override
	public List<GetAllCategoryResponse> getAll() {
		List<Categories> categories = categoryRepository.findAll();
		List<GetAllCategoryResponse> getAllCategoryResponses = new ArrayList<GetAllCategoryResponse>();

		for (Categories category : categories) {
			GetAllCategoryResponse responseItem = new GetAllCategoryResponse();
			BeanUtils.copyProperties(category, responseItem);
			getAllCategoryResponses.add(responseItem);
		}
		return getAllCategoryResponses;
	}

	@Override
	public CategoryResponse addCategory(CreateCategoryRequest createcategoryRequest) {
		Categories category = new Categories();
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setDone(false);
		BeanUtils.copyProperties(createcategoryRequest, category);
		String errorMassage = "";

		if (errorMassage.isEmpty())// boş veri hatası
			errorMassage = emptyErrorCheck(category);
		// başka eklenebilir
		if (errorMassage.isEmpty())// veri tekrarı hatası
			errorMassage = repeatErrorCheck(category);

		if (!errorMassage.isEmpty()) {
			categoryResponse.setErrorMassage(errorMassage);
			categoryResponse.setSuitable(false);
		} else {
			categoryResponse.setSuitable(true);
		}

		if (categoryResponse.getSuitable()) {
			categoryRepository.save(category);
			categoryResponse.setDone(true);
		}

		return categoryResponse;
	}

	@Override
	public void deleteCategory(int id) {
		Optional<Categories> category = categoryRepository.findById(id);

		categoryRepository.delete(category.get());
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public CategoryResponse updateCategoryRequest(UpdateCategoryRequest updateCategoryRequest) {
		Optional<Categories> categoryFromRepository = categoryRepository.findById(updateCategoryRequest.getId());
		Categories categoryUpdate = new Categories();

		BeanUtils.copyProperties(categoryFromRepository.get(), categoryUpdate);
		categoryUpdate = emptyErrorCheckItem(categoryUpdate);
		if (categoryUpdate.getName().equalsIgnoreCase("update")) {
			categoryFromRepository.get().setName(updateCategoryRequest.getName());
		}
		CategoryResponse categoryResponse = new CategoryResponse();

		categoryResponse.setDone(false);
		String errorMassage = "";

		if (errorMassage.isEmpty())// veri tekrarı hatası
			errorMassage = repeatErrorCheck(categoryFromRepository.get());

		if (!errorMassage.isEmpty()) {
			categoryResponse.setErrorMassage(errorMassage);
			categoryResponse.setSuitable(false);
		} else {
			categoryResponse.setSuitable(true);
		}

		if (categoryResponse.getSuitable()) {
			categoryRepository.save(categoryFromRepository.get());
			categoryResponse.setDone(true);
		}
		return categoryResponse;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////
	public String repeatErrorCheck(Categories category) {
		String errorMassage = "";
		if (settings.getNameRepeatErrorCheck()) {
			Optional<Categories> name = categoryRepository.findByNameAndIdNot(category.getName(), category.getId());
			if (name.isPresent()) {
				errorMassage += "Categori ismi tekrar edemez.\n";
			}
		}
		return errorMassage;
	}

	public String emptyErrorCheck(Categories category) {
		String errorMassage = "";
		if (settings.getNameEmtyErrorCheck()) {
			if (category.getName() != null) {
				if (category.getName().isBlank() || category.getName().isEmpty()
						|| category.getName().equalsIgnoreCase("null")) {
					errorMassage += "Categori ismi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Categori ismi boş bırakılamaz \n";
			}
		}
		return errorMassage;
	}

	public Categories emptyErrorCheckItem(Categories category) {
		if (settings.getTitleEmptyErrorCheck()) {
			if (category.getName() != null) {
				if (!category.getName().isBlank() && !category.getName().isEmpty()
						&& !category.getName().equalsIgnoreCase("null")) {
					category.setName("update");
				}
			}
		}
		return category;
	}
}
