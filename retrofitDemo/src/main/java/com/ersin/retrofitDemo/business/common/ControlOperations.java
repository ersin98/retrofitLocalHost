package com.ersin.retrofitDemo.business.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.concretes.Settings;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Product;

@Service
public class ControlOperations {
	private ProductRepository productRepository;
	private Settings settings;

	@Autowired
	public ControlOperations(ProductRepository productRepository, Settings settings) {
		super();
		this.productRepository = productRepository;
		this.settings = settings;
	}

	public String repeatErrorCheck(Product product) {
		String errorMassage = "";
		if (settings.getImageCantRepeat()) {
			Optional<Product> image = productRepository.findByImage(product.getImage());
			if (image.isPresent()) {
				errorMassage += "Resim bilgisi tekrar edemez.\n";
			}
			// image.orElse(null);
		}
		if (settings.getTitleCantRepeat()) {
			Optional<Product> title = productRepository.findByTitle(product.getTitle());
			if (title.isPresent()) {
				errorMassage += "Başlık bilgisi tekrar edemez.\n";
			}
		}
		if (settings.getDescriptionCantRepeat()) {
			Optional<Product> description = productRepository.findByDescription(product.getDescription());
			if (description.isPresent()) {
				errorMassage += "Açıklama bilgisi tekrar edemez.\n";
			}
		}
		// if (settings.getIdCantRepeat()) {
		// Optional<Product> item = productRepository.findById(product.getId());
		// if (item.isPresent()) {
		// errorMassage += "ID tekrar edemez.\n";
		// }
		// }
		return errorMassage;
	}

	public String emptyErrorCheck(Product product) {
		String errorMassage = "";
		if (settings.getImageCantEmpty()) {

		}
		if (settings.getTitleCantEmpty()) {
			if (product.getTitle() != null) {
				if (product.getTitle().isBlank() || product.getTitle().isEmpty()
						|| product.getTitle().equalsIgnoreCase("null")) {
					errorMassage += "Başlık bilgisi boş bırakılamaz";
				}
			} else {
				errorMassage += "Başlık bilgisi boş bırakılamaz";
			}
		}
		if (settings.getImageCantEmpty()) {
			if (product.getImage() != null) {
				if (product.getImage().isBlank() || product.getImage().isEmpty()
						|| product.getImage().equalsIgnoreCase("null")) {
					errorMassage += "Başlık bilgisi boş bırakılamaz";
				}
			} else {
				errorMassage += "Görsel bilgisi boş bırakılamaz";
			}
		}
		if (settings.getDescriptionCantEmpty()) {
			if (product.getDescription() != null) {
				if (product.getDescription().isBlank() || product.getDescription().isEmpty()
						|| product.getDescription().equalsIgnoreCase("null")) {
					errorMassage += "Açıklama bilgisi boş bırakılamaz";
				}
			} else {
				errorMassage += "Başlık bilgisi boş bırakılamaz";
			}
		}

		if (settings.getPriceCantEmpty()) {
			if (product.getPrice() != null) {
				if (product.getPrice().toString().isBlank() || product.getPrice().toString().isEmpty()
						|| product.getPrice().toString().equalsIgnoreCase("null")) {
					errorMassage += "Fiyat bilgisi boş bırakılamaz";
				}
			} else {
				errorMassage += "Fiyat bilgisi boş bırakılamaz";
			}
		}
		return errorMassage;
	}
}
