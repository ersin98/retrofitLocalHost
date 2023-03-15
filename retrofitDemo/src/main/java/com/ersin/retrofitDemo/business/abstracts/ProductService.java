package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByCategoryProductResponse;

public interface ProductService {
	List<GetAllProductResponse> getAll();

	void addProduct(CreateProductRequest createProductRequest);

	List<GetByQueryProductResponse> getByTitle(String query);

	void deleteProduct(int id);

	void deleteAll();

	void updateProductRequest(UpdateProductRequest productRequest);

	List<GetByCategoryProductResponse> getByCategory(Integer id);

}
