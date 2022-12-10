package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.productRequests.CreateProductRequest;
import com.ersin.retrofitDemo.business.responses.productResponses.GetAllProductResponse;

public interface ProductService {
	List<GetAllProductResponse> getAll();

	void addProduct(CreateProductRequest createProductRequests);
}
