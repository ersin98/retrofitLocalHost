package com.ersin.retrofitDemo.webApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.requests.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.UpdateProductRequest;
import com.ersin.retrofitDemo.business.requests.controllers.CreateProducRequestController;
import com.ersin.retrofitDemo.business.responses.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.GetByQueryProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/getall")
	List<GetAllProductResponse> getAll() {
		return productService.getAll();
	}

	@GetMapping("/getbytitle")
	List<GetByQueryProductResponse> getByQueryProductResponse(String title) {
		return productService.getByTitle(title);
	}

	@PostMapping("/add")
	public CreateProducRequestController addProduct(@RequestBody CreateProductRequest createProductRequest) {
		return productService.addProduct(createProductRequest);
	}

	@PostMapping("/delete")
	public void deleteProduct(int id) {
		productService.deleteProduct(id);
	}

	@PostMapping("/deleteall")
	public void deleteProduct() {
		productService.deleteAll();
	}

	@PostMapping("/update")
	public void updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
		productService.updateProductRequest(updateProductRequest);
	}
}
