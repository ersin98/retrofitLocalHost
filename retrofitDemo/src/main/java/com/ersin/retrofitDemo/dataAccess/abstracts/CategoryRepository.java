package com.ersin.retrofitDemo.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean existsByName(String name);
}
