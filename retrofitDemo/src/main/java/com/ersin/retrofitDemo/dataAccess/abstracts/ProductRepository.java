package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<List<Product>> findByTitleContaining(String Query);
	// Optional<List<Product>> findByTitleLike(String Query);
	// Optional<List<Product>> findByTitleStartingWith(String Query);

	// Optional<Product> findByImage(String string);//Id sınırlaması yok
	// Optional<Product> findByDescription(String description);
	// Optional<Product> findByTitle(String title);

	Optional<Product> findByTitleAndIdNot(String title, Integer id);

	Optional<Product> findByDescriptionAndIdNot(String description, Integer id);

	Optional<Product> findByImageAndIdNot(String string, Integer id);
}
