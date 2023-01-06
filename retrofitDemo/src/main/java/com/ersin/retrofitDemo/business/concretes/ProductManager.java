package com.ersin.retrofitDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.requests.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.GetByQueryProductResponse;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {
	private ProductRepository productRepository;

	@Autowired
	public ProductManager(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<GetAllProductResponse> getAll() {

		List<Product> products = productRepository.findAll();
		List<GetAllProductResponse> getAllProductResponses = new ArrayList<GetAllProductResponse>();

		for (Product product : products) {
			GetAllProductResponse responseItem = new GetAllProductResponse();

			responseItem.setId(product.getId());
			responseItem.setDescription(product.getDescription());
			responseItem.setImageData(product.getImageData());
			responseItem.setPrice(product.getPrice());
			responseItem.setTitle(product.getTitle());
			getAllProductResponses.add(responseItem);
		}
		return getAllProductResponses;
	}

	@Override
	public void addProduct(CreateProductRequest createProductRequest) throws Throwable {
		Product product = new Product();
		if (createProductRequest.getTitle() == "null" || createProductRequest.getTitle().isEmpty()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		product.setDescription(createProductRequest.getDescription());
		product.setImageData(createProductRequest.getImageData());
		product.setPrice(createProductRequest.getPrice());
		product.setTitle(createProductRequest.getTitle());
		this.productRepository.save(product);
		throw new IllegalArgumentException("Title cannot be empty");
		// throw new Exception("Title cannot be empty");

	}

	public void check() {

	}

	@Override
	public List<GetByQueryProductResponse> getByTitle(String title) {
		List<Product> products = null;
		if (title.isBlank() || title.isEmpty()) {
			products = productRepository.findAll();
		} else {
			Optional<List<Product>> getProduct = productRepository.findByTitleContaining(title);
			products = getProduct.get();
		}
		List<GetByQueryProductResponse> byQueryProductResponses = new ArrayList<GetByQueryProductResponse>();
		for (Product product : products) {
			GetByQueryProductResponse byQueryProductResponse = new GetByQueryProductResponse();
			byQueryProductResponse.setDescription(product.getDescription());
			byQueryProductResponse.setImageData(product.getImageData());
			byQueryProductResponse.setPrice(product.getPrice());
			byQueryProductResponse.setTitle(product.getTitle());
			byQueryProductResponse.setId(product.getId());
			byQueryProductResponses.add(byQueryProductResponse);
		}
		return byQueryProductResponses;
	}

	@Override
	public void deleteProduct(int id) {
		Optional<Product> product = productRepository.findById(id);
		productRepository.delete(product.get());
	}

	@Override
	public void updateProductRequest(UpdateProductRequest updateProductRequest) {
		List<Product> products = productRepository.findAll();
		Optional<Product> item = productRepository.findById(updateProductRequest.getId());
		Product productFromRepository = item.get();
		for (Product product : products) {
			if (updateProductRequest.getId() == productFromRepository.getId()) {
				productFromRepository.setDescription(updateProductRequest.getDescription());
				productFromRepository.setImageData(updateProductRequest.getImageData());
				productFromRepository.setPrice(updateProductRequest.getPrice());
				productFromRepository.setTitle(updateProductRequest.getTitle());
				productRepository.save(productFromRepository);
			}
		}
	}

}
