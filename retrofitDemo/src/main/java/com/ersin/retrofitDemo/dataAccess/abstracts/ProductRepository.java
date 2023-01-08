package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<List<Product>> findByTitleContaining(String Query);
	// Optional<List<Product>> findByTitleLike(String Query);
	// Optional<List<Product>> findByTitleStartingWith(String Query);

	Optional<Product> findByImage(String string);

	Optional<Product> findByDescription(String description);

	Optional<Product> findByTitle(String title);
}
