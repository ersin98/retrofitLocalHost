package com.ersin.retrofitDemo.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByCategoryProductResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {
	private ProductService productService;

	@GetMapping()
	List<GetAllProductResponse> getAll() {
		return productService.getAll();
	}

	@GetMapping()
	List<GetByQueryProductResponse> getByQueryProductResponse(String title) {
		return productService.getByTitle(title);
	}

	@GetMapping("/{id}")
	List<GetByCategoryProductResponse> getByCategoryProductResponse(Integer id) {
		return productService.getByCategory(id);
	}

	@PostMapping()
	public void addProduct(@RequestBody CreateProductRequest createProductRequest) {
		productService.addProduct(createProductRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(int id) {
		productService.deleteProduct(id);
	}

	@DeleteMapping()
	public void deleteAll() {
		productService.deleteAll();
	}

	@PutMapping()
	public void updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
		productService.updateProductRequest(updateProductRequest);
	}
}
