package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.CreateProductRequest;
import com.ersin.retrofitDemo.business.responses.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.GetByQueryProductResponse;

public interface ProductService {
	List<GetAllProductResponse> getAll();

	void addProduct(CreateProductRequest createProductRequests);

	List<GetByQueryProductResponse> getByTitle(String query);

}
