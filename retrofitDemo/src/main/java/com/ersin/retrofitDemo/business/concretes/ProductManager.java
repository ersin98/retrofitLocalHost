package com.ersin.retrofitDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.requests.productRequests.CreateProductRequest;
import com.ersin.retrofitDemo.business.responses.productResponses.GetAllProductResponse;
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
			responseItem.setImageId(product.getImageId());
			responseItem.setPrice(product.getPrice());
			responseItem.setTitle(product.getTitle());
			getAllProductResponses.add(responseItem);
		}
		return getAllProductResponses;
	}

	@Override
	public void addProduct(CreateProductRequest createProductRequests) {
		Product product = new Product();
		product.setDescription(createProductRequests.getDescription());
		product.setImageId(createProductRequests.getImageId());
		product.setPrice(createProductRequests.getPrice());
		product.setTitle(createProductRequests.getTitle());
		this.productRepository.save(product);
	}

}
