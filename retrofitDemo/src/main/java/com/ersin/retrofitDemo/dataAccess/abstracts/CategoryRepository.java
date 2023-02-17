package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {

	Optional<Categories> findByNameAndIdNot(String string, Integer id);
}
