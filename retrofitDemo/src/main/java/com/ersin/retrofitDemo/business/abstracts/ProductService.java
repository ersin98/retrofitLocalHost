package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.UpdateProductRequest;
import com.ersin.retrofitDemo.business.requests.controllers.CreateProductRequestController;
import com.ersin.retrofitDemo.business.responses.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.GetByQueryProductResponse;

public interface ProductService {
	List<GetAllProductResponse> getAll();

	CreateProductRequestController addProduct(CreateProductRequest createProductRequest);

	List<GetByQueryProductResponse> getByTitle(String query);

	void deleteProduct(int id);

	void deleteAll();

	void updateProductRequest(UpdateProductRequest productRequest);

}
