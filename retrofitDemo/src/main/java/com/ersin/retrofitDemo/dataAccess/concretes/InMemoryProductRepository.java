package com.ersin.retrofitDemo.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Product;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	List<Product> products;

	public InMemoryProductRepository() {
		products = new ArrayList<Product>();
		products.add(new Product("acıklama", 1, "resim", 10.6, "başlık"));
		products.add(new Product("acklamama", 2, "görsel", 14.1, "header"));
		products.add(new Product("uzunmetin", 3, "çizim", 14.1, "kısametin"));
	}

	@Override
	public List<Product> getAll() {
		return products;
	}

}
