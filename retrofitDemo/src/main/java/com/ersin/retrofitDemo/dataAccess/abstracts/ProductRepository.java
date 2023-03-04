package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Categories;
import com.ersin.retrofitDemo.entities.concretes.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
//kullanmazsam ayıklamam gerekenler var

	Optional<List<Products>> findByTitleContaining(String Query);
	// Optional<List<Product>> findByTitleLike(String Query);
	// Optional<List<Product>> findByTitleStartingWith(String Query);

	// Optional<Product> findByImage(String string);//Id sınırlaması yok
	// Optional<Product> findByDescription(String description);
	// Optional<Product> findByTitle(String title);

	Optional<Products> findByTitleAndIdNot(String title, Integer id);

	Optional<Products> findByDescriptionAndIdNot(String description, Integer id);

	Optional<Products> findByImageAndIdNot(String string, Integer id);

	Optional<List<Products>> findByCategory(Categories category);

	boolean existsByTitle(String name);

	boolean existsByImage(String name);
}
