package com.ersin.retrofitDemo.business.abstracts;

import java.util.List;

import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.GetCategoryProductResponse;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.ProductResponse;

public interface ProductService {
	List<GetAllProductResponse> getAll();

	ProductResponse addProduct(CreateProductRequest createProductRequest);

	List<GetByQueryProductResponse> getByTitle(String query);

	void deleteProduct(int id);

	void deleteAll();

	ProductResponse updateProductRequest(UpdateProductRequest productRequest);

	List<GetCategoryProductResponse> getByCategory(Integer id);

}
