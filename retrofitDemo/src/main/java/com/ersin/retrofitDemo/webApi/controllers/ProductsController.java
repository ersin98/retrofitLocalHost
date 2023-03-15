package com.ersin.retrofitDemo.webApi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByCategoryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;

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

	@GetMapping("/{title}")
	List<GetByQueryProductResponse> getByQueryProductResponse(@PathVariable String title) {
		return productService.getByTitle(title);
	}

	@GetMapping("/category/{categoryId}")
	List<GetByCategoryProductResponse> getByCategoryProductResponse(int id) {
		return productService.getByCategory(id);
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public void addProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
		productService.addProduct(createProductRequest);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
	}

	@DeleteMapping()
	public void deleteAll() {
		productService.deleteAll();
	}

	@PutMapping()
	public void updateProduct(@RequestBody @Valid UpdateProductRequest updateProductRequest) {
		productService.updateProductRequest(updateProductRequest);
	}
}
