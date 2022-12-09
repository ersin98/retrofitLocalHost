package com.ersin.retrofitDemo.entities.concretes;

public class Product {
	private String description;
	private int id;
	private String imageData;
	private Double price;
	private String title;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Product(String description, int id, String imageData, Double price, String title) {
		super();
		this.description = description;
		this.id = id;
		this.imageData = imageData;
		this.price = price;
		this.title = title;
	}

	public Product() {
		super();
	}
}
