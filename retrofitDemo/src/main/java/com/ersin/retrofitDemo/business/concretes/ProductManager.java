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
import com.ersin.retrofitDemo.business.rules.ProductRules;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Category;
import com.ersin.retrofitDemo.entities.concretes.Product;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private ModelMapperService mapperService;
	private ProductRules productRules;

	@Override
	public List<GetAllProductResponse> getAll() {
		List<Product> products = productRepository.findAll();
		List<GetAllProductResponse> productResponses = products.stream()
				.map(product -> mapperService.forResponse().map(product, GetAllProductResponse.class))
				.collect(Collectors.toList());
		// .peek(response->response.setCategoryID(response.getCategoryID())).collect(Collectors.toList());
		return productResponses;
	}

	@Override
	public void addProduct(CreateProductRequest createProductRequest) {
		Product product = mapperService.forRequest().map(createProductRequest, Product.class);
		Category category = categoryRepository.findById(createProductRequest.getCategoryId()).orElseThrow();
		product.setCategory(category);
		productRules.checkIfProductTitleExists(product.getTitle());
		productRules.checkIfProductImageExists(product.getImage());
		productRepository.save(product);
	}

	@Override
	public List<GetByQueryProductResponse> getByTitle(String title) {

		List<Product> productFromRepository = productRepository.findByTitleContaining(title).orElseThrow();
		List<GetByQueryProductResponse> productResponses = productFromRepository.stream()
				.map(product -> mapperService.forResponse().map(product, GetByQueryProductResponse.class))
				.collect(Collectors.toList());
		return productResponses;
	}

	@Override
	public void deleteProduct(int id) {
		Product product = productRepository.findById(id).orElseThrow();
		productRepository.delete(product);
	}

	@Override
	public void updateProductRequest(UpdateProductRequest updateProductRequest) {
		Product product = mapperService.forRequest().map(updateProductRequest, Product.class);
		Category category = categoryRepository.findById(updateProductRequest.getCategoryID()).orElseThrow();
		product.setCategory(category);
		productRepository.save(product);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public List<GetByCategoryProductResponse> getByCategory(Integer id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		List<Product> productFromRepository = productRepository.findByCategory(category).orElseThrow();
		List<GetByCategoryProductResponse> ProductResponse = productFromRepository.stream()
				.map(product -> mapperService.forResponse().map(product, GetByCategoryProductResponse.class))
				.collect(Collectors.toList());

		return ProductResponse;
	}
}
