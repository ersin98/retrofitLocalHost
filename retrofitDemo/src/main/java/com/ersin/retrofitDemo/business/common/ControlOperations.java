package com.ersin.retrofitDemo.business.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Product;

public class ControlOperations {
	private ProductRepository productRepository;

	@Autowired
	public ControlOperations(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public String repeatErrorCheck(Product product) {
		Optional<Product> id = productRepository.findById(product.getId());

		Optional<Product> result = productRepository.findByDescription(product.getDescription());// Settings ayarla

		Product product2 = result.orElse(null);
		if (result.isPresent()) {

		}

		// Product productFromRepository = item.get();

		// Optional<List<Product>> getProduct =
		// productRepository.findByTitleContaining(title);
		// products = getProduct.get();

		return "dafdw";
	}
}
