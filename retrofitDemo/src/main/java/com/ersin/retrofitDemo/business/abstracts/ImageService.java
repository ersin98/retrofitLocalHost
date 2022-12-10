package com.ersin.retrofitDemo.business.abstracts;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	void addImageRequest(MultipartFile file) throws IOException;
}
