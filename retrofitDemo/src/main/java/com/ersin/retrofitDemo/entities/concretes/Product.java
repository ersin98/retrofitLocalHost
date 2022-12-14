package com.ersin.retrofitDemo.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Data // @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Column(name = "description", columnDefinition = "text")
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "imageData")
	private String imageData;
	@Column(name = "price")
	private Double price;
	@Column(name = "title")
	private String title;

}
