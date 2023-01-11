package com.ersin.retrofitDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.common.ControlOperations;
import com.ersin.retrofitDemo.business.requests.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.UpdateProductRequest;
import com.ersin.retrofitDemo.business.requests.controllers.CreateProducRequestController;
import com.ersin.retrofitDemo.business.responses.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.GetByQueryProductResponse;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Product;

@Service
public class ProductManager implements ProductService {
	private ProductRepository productRepository;
	private ControlOperations controlOperations;

	@Autowired
	public ProductManager(ProductRepository productRepository, ControlOperations controlOperations) {
		super();
		this.productRepository = productRepository;
		this.controlOperations = controlOperations;
	}

	@Override
	public List<GetAllProductResponse> getAll() {

		List<Product> products = productRepository.findAll();
		List<GetAllProductResponse> getAllProductResponses = new ArrayList<GetAllProductResponse>();

		for (Product product : products) {
			GetAllProductResponse responseItem = new GetAllProductResponse();
			BeanUtils.copyProperties(responseItem, product);
			getAllProductResponses.add(responseItem);
		}
		return getAllProductResponses;
	}

	@Override
	public CreateProducRequestController addProduct(CreateProductRequest createProductRequest) {
		CreateProducRequestController createProducRequestController = new CreateProducRequestController();
		createProducRequestController.setDone(false);
		Product product = new Product();
		BeanUtils.copyProperties(createProductRequest, product);
		String errorMassage = "";
		if (errorMassage.isEmpty())// boş veri hatası
			errorMassage = controlOperations.emptyErrorCheck(product);
		// başka eklenebilir
		if (errorMassage.isEmpty())// veri tekrarı hatası
			errorMassage = controlOperations.repeatErrorCheck(product);

		if (!errorMassage.isEmpty()) {
			createProducRequestController.setErrorMassage(errorMassage);
			createProducRequestController.setSuitable(false);
		} else {
			createProducRequestController.setSuitable(true);
		}

		if (createProducRequestController.getSuitable()) {
			productRepository.save(product);
			createProducRequestController.setDone(true);
		}
		return createProducRequestController;
	}

	@Override
	public List<GetByQueryProductResponse> getByTitle(String title) {
		List<Product> products = null;
		if (title.isBlank() || title.isEmpty()) {
			products = productRepository.findAll();
		} else {
			Optional<List<Product>> getProduct = productRepository.findByTitleContaining(title);
			products = getProduct.get();
		}
		List<GetByQueryProductResponse> byQueryProductResponses = new ArrayList<GetByQueryProductResponse>();
		for (Product product : products) {
			GetByQueryProductResponse byQueryProductResponse = new GetByQueryProductResponse();
			BeanUtils.copyProperties(product, byQueryProductResponse);
			byQueryProductResponses.add(byQueryProductResponse);
		}
		return byQueryProductResponses;
	}

	@Override
	public void deleteProduct(int id) {

		Optional<Product> product = productRepository.findById(id);
		productRepository.delete(product.get());
	}

	@Override
	public void updateProductRequest(UpdateProductRequest updateProductRequest) {
		Optional<Product> item = productRepository.findById(updateProductRequest.getId());
		Product productFromRepository = item.get();
		BeanUtils.copyProperties(updateProductRequest, productFromRepository);
		productRepository.save(productFromRepository);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

}
