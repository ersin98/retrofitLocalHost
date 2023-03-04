package com.ersin.retrofitDemo.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.core.utilities.mappers.ModelMapperService;
import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByCategoryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Categories;
import com.ersin.retrofitDemo.entities.concretes.Products;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class ProductManager implements ProductService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private ModelMapperService mapperService;

	@Override
	public List<GetAllProductResponse> getAll() {
		List<Products> products = productRepository.findAll();
		List<GetAllProductResponse> productResponses = products.stream()
				.map(product -> mapperService.forResponse().map(product, GetAllProductResponse.class))
				.collect(Collectors.toList());
		// .peek(response->response.setCategoryID(response.getCategoryID())).collect(Collectors.toList());
		return productResponses;
	}

	@Override
	public void addProduct(CreateProductRequest createProductRequest) {
		Products product = mapperService.forRequest().map(createProductRequest, Products.class);
		Categories category = categoryRepository.findById(createProductRequest.getCategoryID()).orElseThrow();
		product.setCategory(category);
		productRepository.save(product);
	}

	@Override
	public List<GetByQueryProductResponse> getByTitle(String title) {
		List<Products> productFromRepository = productRepository.findByTitleContaining(title).orElseThrow();
		List<GetByQueryProductResponse> productResponses = productFromRepository.stream()
				.map(product -> mapperService.forResponse().map(product, GetByQueryProductResponse.class))
				.collect(Collectors.toList());
		return productResponses;
	}

	@Override
	public void deleteProduct(int id) {
		Products product = productRepository.findById(id).orElseThrow();
		productRepository.delete(product);
	}

	@Override
	public void updateProductRequest(UpdateProductRequest updateProductRequest) {
		productRepository.findById(updateProductRequest.getId()).orElseThrow();// güncellenecek kayıt var mı ona bakıyor
		Products product = mapperService.forRequest().map(updateProductRequest, Products.class);
		Categories category = categoryRepository.findById(updateProductRequest.getCategoryID()).orElseThrow();
		product.setCategory(category);
		productRepository.save(product);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public List<GetByCategoryProductResponse> getByCategory(Integer id) {
		Categories category = categoryRepository.findById(id).orElseThrow();
		List<Products> productFromRepository = productRepository.findByCategory(category).orElseThrow();
		List<GetByCategoryProductResponse> ProductResponse = productFromRepository.stream()
				.map(product -> mapperService.forResponse().map(product, GetByCategoryProductResponse.class))
				.collect(Collectors.toList());

		return ProductResponse;
	}
}
