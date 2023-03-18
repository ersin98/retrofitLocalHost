package com.ersin.retrofitDemo.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ersin.retrofitDemo.entities.concretes.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	Optional<Image> findByName(String fileName);

}