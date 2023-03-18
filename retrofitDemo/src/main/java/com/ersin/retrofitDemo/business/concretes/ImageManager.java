package com.ersin.retrofitDemo.business.concretes;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ersin.retrofitDemo.business.abstracts.ImageService;
import com.ersin.retrofitDemo.dataAccess.abstracts.ImageRepository;
import com.ersin.retrofitDemo.entities.concretes.Image;

@Service
public class ImageManager implements ImageService {
	private ImageRepository imageRepository;

	public ImageManager(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}

	@Override
	public void addImageRequest(MultipartFile file) throws IOException {
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setImageData(file.getBytes());
		imageRepository.save(image);
	}

}