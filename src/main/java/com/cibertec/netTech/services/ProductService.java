package com.cibertec.netTech.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.netTech.models.Product;
import com.cibertec.netTech.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private Environment env;

	@Autowired
	ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void deleteById(long id) {
		productRepository.deleteById(id);
	}

	public Long total() {
		return productRepository.count();
	}

	public Optional<Product> findById(Long productId) {
		return productRepository.findById(productId);
	}

	public Product save(Product p) {
		return productRepository.save(p);
	}

	public String saveImage(MultipartFile file) throws IOException {
		String uploadDir = env.getProperty("app.upload.dir");
		Path uploadPath = Paths.get(uploadDir + "/products");

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		if (!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadPath + "/" + file.getOriginalFilename());
			Files.write(path, bytes);
			return "products/" + file.getOriginalFilename();
		}
		return "default.png";
	}
}
