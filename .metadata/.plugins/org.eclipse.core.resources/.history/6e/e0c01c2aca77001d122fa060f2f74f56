package com.ersin.retrofitDemo.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
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
	public List<Product> getAll() {
		return productRepository.getAll();
	}

}
