package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Category;
import com.ersin.retrofitDemo.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
//kullanmazsam ayıklamam gerekenler var

	Optional<List<Product>> findByTitleContaining(String Query);
	// Optional<List<Product>> findByTitleLike(String Query);
	// Optional<List<Product>> findByTitleStartingWith(String Query);

	Optional<List<Product>> findByCategory(Category category);

	boolean existsByTitle(String name);

	boolean existsByImage(String name);

	// AndIdNot
}
