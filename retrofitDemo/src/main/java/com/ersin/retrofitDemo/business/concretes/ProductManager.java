package com.ersin.retrofitDemo.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ersin.retrofitDemo.business.abstracts.ProductService;
import com.ersin.retrofitDemo.business.common.Settings;
import com.ersin.retrofitDemo.business.requests.product.CreateProductRequest;
import com.ersin.retrofitDemo.business.requests.product.UpdateProductRequest;
import com.ersin.retrofitDemo.business.responses.product.GetAllProductResponse;
import com.ersin.retrofitDemo.business.responses.product.GetByQueryProductResponse;
import com.ersin.retrofitDemo.business.responses.product.ProductResponse;
import com.ersin.retrofitDemo.dataAccess.abstracts.CategoryRepository;
import com.ersin.retrofitDemo.dataAccess.abstracts.ProductRepository;
import com.ersin.retrofitDemo.entities.concretes.Categories;
import com.ersin.retrofitDemo.entities.concretes.Products;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductManager implements ProductService {
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private Settings settings;
	List<Products> update = null;// emptyErrorCheckItem içerisindeki kategori için update yazan bir product

	@Autowired
	public ProductManager(ProductRepository productRepository, CategoryRepository categoryRepository,
			Settings settings) {
		super();
		this.productRepository = productRepository;
		this.settings = settings;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<GetAllProductResponse> getAll() {
		List<Products> products = productRepository.findAll();
		List<GetAllProductResponse> getAllProductResponses = new ArrayList<GetAllProductResponse>();

		for (Products product : products) {
			GetAllProductResponse responseItem = new GetAllProductResponse();
			BeanUtils.copyProperties(product, responseItem);
			getAllProductResponses.add(responseItem);
		}
		return getAllProductResponses;
	}

	@Override
	public ProductResponse addProduct(CreateProductRequest createProductRequest) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setDone(false);
		Products product = new Products();
		BeanUtils.copyProperties(createProductRequest, product);
		if (createProductRequest.getCategoryID() != null) {
			Optional<Categories> categories = categoryRepository.findById(createProductRequest.getCategoryID());
			product.setCategory(categories.get());
		}
		String errorMassage = "";
		if (errorMassage.isEmpty())// boş veri hatası
			errorMassage = emptyErrorCheck(product);
		// başka eklenebilir
		if (errorMassage.isEmpty())// veri tekrarı hatası
			errorMassage = repeatErrorCheck(product);

		if (!errorMassage.isEmpty()) {
			productResponse.setErrorMassage(errorMassage);
			productResponse.setSuitable(false);
		} else {
			productResponse.setSuitable(true);
		}

		if (productResponse.getSuitable()) {
			productRepository.save(product);
			productResponse.setDone(true);
		}
		return productResponse;
	}

	@Override
	public List<GetByQueryProductResponse> getByTitle(String title) {
		List<Products> products = null;
		if (title.isBlank() || title.isEmpty()) {
			products = productRepository.findAll();
		} else {
			Optional<List<Products>> getProduct = productRepository.findByTitleContaining(title);
			products = getProduct.get();
		}
		List<GetByQueryProductResponse> byQueryProductResponses = new ArrayList<GetByQueryProductResponse>();
		for (Products product : products) {
			GetByQueryProductResponse byQueryProductResponse = new GetByQueryProductResponse();
			BeanUtils.copyProperties(product, byQueryProductResponse);
			byQueryProductResponses.add(byQueryProductResponse);
		}
		return byQueryProductResponses;
	}

	@Override
	public void deleteProduct(int id) {
		Optional<Products> product = productRepository.findById(id);
		productRepository.delete(product.get());
	}

	@Override
	public ProductResponse updateProductRequest(UpdateProductRequest updateProductRequest) {
		log.info("NERDE KALDIK ::: update işlemi başladı");
		Optional<Products> productFromRepository = productRepository.findById(updateProductRequest.getId());// .get()
		ProductResponse productResponse = new ProductResponse();
		Products Updateproduct = new Products();
		BeanUtils.copyProperties(updateProductRequest, Updateproduct);
		if (updateProductRequest.getCategoryID() != null) {
			Optional<Categories> categories = categoryRepository.findById(updateProductRequest.getCategoryID());
			Updateproduct.setCategory(categories.get());
		}
		Updateproduct = emptyErrorCheckItem(Updateproduct);
		if (Updateproduct.getTitle().equalsIgnoreCase("update")) {
			productFromRepository.get().setTitle(updateProductRequest.getTitle());
		}
		if (Updateproduct.getImage().equalsIgnoreCase("update")) {
			productFromRepository.get().setImage(updateProductRequest.getImage());
		}
		if (Updateproduct.getPrice().toString().equalsIgnoreCase("1.1")) {
			productFromRepository.get().setPrice(updateProductRequest.getPrice());
		}
		if (Updateproduct.getDescription().equalsIgnoreCase("update")) {
			productFromRepository.get().setDescription(updateProductRequest.getDescription());
		}
		log.info("NERDE KALDIK ::: Updateproduct.getCategory().toString() = " + Updateproduct.getCategory().toString());
		if (Updateproduct.getCategory().toString().equalsIgnoreCase("1,update,update")) {// test etmedim
			productFromRepository.get().setDescription(updateProductRequest.getDescription());

		}
		productResponse.setDone(false);
		String errorMassage = "";

		if (errorMassage.isEmpty())// veri tekrarı hatası

			errorMassage = Updateproduct.getCategory().toString(); // repeatErrorCheck(productFromRepository.get());

		if (!errorMassage.isEmpty()) {
			productResponse.setErrorMassage(errorMassage);
			productResponse.setSuitable(false);
		} else {
			productResponse.setSuitable(true);
		}

		if (productResponse.getSuitable()) {
			productRepository.save(productFromRepository.get());
			productResponse.setDone(true);
		}
		return productResponse;

	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	///////////////////////////////////////////////////////////////////////////////////
	public String repeatErrorCheck(Products product) {
		String errorMassage = "";
		if (settings.getImageRepeatErrorCheck()) {
			Optional<Products> image = productRepository.findByImageAndIdNot(product.getImage(), product.getId());
			if (image.isPresent()) {
				errorMassage += "Resim bilgisi tekrar edemez.\n";
			}
			// image.orElse(null);
		}
		if (settings.getTitleRepeatErrorCheck()) {
			// kendisi dışındakileri arıyor
			Optional<Products> title = productRepository.findByTitleAndIdNot(product.getTitle(), product.getId());

			if (title.isPresent()) {
				errorMassage += "Başlık bilgisi tekrar edemez.\n" + "sonuç:" + title;
			}
		}
		if (settings.getDescriptionRepeatErrorCheck()) {
			Optional<Products> description = productRepository.findByDescriptionAndIdNot(product.getDescription(),
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

	public String emptyErrorCheck(Products product) {
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

		if (settings.getCategoryEmptyErrorCheck()) {
			log.info("bakalım nasıl yazıyor: " + product.getCategory().toString());
			if (product.getCategory() != null) {
/////////////////////////////////////////////////////////////////////////////////////////////////////////
				System.out.println("bakalım nasıl yazıyor: " + product.getCategory().toString());
				if (product.getCategory().toString().isBlank() || product.getCategory().toString().isEmpty()
						|| product.getCategory().toString().equalsIgnoreCase("null")) {
					errorMassage += "Kategori bilgisi boş bırakılamaz \n";
				}
			} else {
				errorMassage += "Kategori bilgisi boş bırakılamaz \n";
			}
		}
		log.debug("Bak bu debug :" + errorMassage);
		log.info("Bak bu info :" + errorMassage);
		log.warn("Bak bu warn :" + errorMassage);
		log.trace("Bak bu trace: " + errorMassage);
		return errorMassage;
	}

	public Products emptyErrorCheckItem(Products product) {
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

		if (settings.getCategoryEmptyErrorCheck()) {
			if (product.getCategory() != null) {
				if (!product.getCategory().toString().isBlank() && !product.getCategory().toString().isEmpty()
						&& !product.getCategory().toString().equalsIgnoreCase("null")) {
					product.setCategory(new Categories(1, "update", update));
				}
			}
		}

		return product;
	}
}
