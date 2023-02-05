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
		if (settings.getImageRepeatErrorCheck()) {
			Optional<Product> image = productRepository.findByImageAndIdNot(product.getImage(), product.getId());
			if (image.isPresent()) {
				errorMassage += "Resim bilgisi tekrar edemez.\n";
			}
			// image.orElse(null);
		}
		if (settings.getTitleRepeatErrorCheck()) {
			// kendisi dışındakileri arıyor
			Optional<Product> title = productRepository.findByTitleAndIdNot(product.getTitle(), product.getId());

			if (title.isPresent()) {
				errorMassage += "Başlık bilgisi tekrar edemez.\n" + "sonuç:" + title;
			}
		}
		if (settings.getDescriptionRepeatErrorCheck()) {
			Optional<Product> description = productRepository.findByDescriptionAndIdNot(product.getDescription(),
					product.getId());
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
		if (settings.getTitleEmptyErrorCheck()) {
			if (product.getTitle() != null) {
				if (product.getTitle().isBlank() || product.getTitle().isEmpty()
						|| product.getTitle().equalsIgnoreCase("null")) {
					errorMassage += "Başlık bilgisi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Başlık bilgisi boş bırakılamaz \n";
			}
		}
		if (settings.getImageEmptyErrorCheck()) {
			if (product.getImage() != null) {
				if (product.getImage().isBlank() || product.getImage().isEmpty()
						|| product.getImage().equalsIgnoreCase("null")) {
					errorMassage += "Görsel bilgisi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Görsel bilgisi boş bırakılamaz \n";
			}
		}
		if (settings.getDescriptionEmptyErrorCheck()) {
			if (product.getDescription() != null) {
				if (product.getDescription().isBlank() || product.getDescription().isEmpty()
						|| product.getDescription().equalsIgnoreCase("null")) {
					errorMassage += "Açıklama bilgisi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Açıklama bilgisi boş bırakılamaz \n";
			}
		}

		if (settings.getPriceEmptyErrorCheck()) {
			if (product.getPrice() != null) {
				if (product.getPrice().toString().isBlank() || product.getPrice().toString().isEmpty()
						|| product.getPrice().toString().equalsIgnoreCase("null")
						|| product.getPrice().toString().equalsIgnoreCase("0.0")) {
					errorMassage += "Fiyat bilgisi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Fiyat bilgisi boş bırakılamaz \n";
			}
		}
		return errorMassage;
	}

	public Product emptyErrorCheckItem(Product product) {
		if (settings.getTitleEmptyErrorCheck()) {
			if (product.getTitle() != null) {
				if (!product.getTitle().isBlank() && !product.getTitle().isEmpty()
						&& !product.getTitle().equalsIgnoreCase("null")) {
					product.setTitle("update");
				}
			}
		}
		if (settings.getImageEmptyErrorCheck()) {
			if (product.getImage() != null) {
				if (!product.getImage().isBlank() && !product.getImage().isEmpty()
						&& !product.getImage().equalsIgnoreCase("null")) {
					product.setImage("update");
				}
			}
		}

		if (settings.getDescriptionEmptyErrorCheck()) {
			if (product.getDescription() != null) {
				if (!product.getDescription().isBlank() && !product.getDescription().isEmpty()
						&& !product.getDescription().equalsIgnoreCase("null")) {
					product.setDescription("update");
				}
			}
		}

		if (settings.getPriceEmptyErrorCheck()) {
			if (product.getPrice() != null) {
				if (!product.getPrice().toString().isBlank() && !product.getPrice().toString().isEmpty()
						&& !product.getPrice().toString().equalsIgnoreCase("null")
						&& !product.getPrice().toString().equalsIgnoreCase("0.0")) {
					product.setPrice(1.1);
				}
			}
		}
		return product;
	}
}
