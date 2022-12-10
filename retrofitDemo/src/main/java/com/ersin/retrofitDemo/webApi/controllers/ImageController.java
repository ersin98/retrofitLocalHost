package com.ersin.retrofitDemo.webApi.controllers;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ersin.retrofitDemo.business.abstracts.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {
	private ImageService imageService;

	public ImageController(ImageService imageService) {
		super();
		this.imageService = imageService;
	}

	@PostMapping("/add")
	public void addImage(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
		imageService.addImageRequest(file);
		// redirectAttributes.addFlashAttribute("message",
		// "You successfully uploaded " + file.getOriginalFilename() + "!");
		// return "redirect:/";
	}
}
