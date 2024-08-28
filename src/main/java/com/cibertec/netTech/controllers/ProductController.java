package com.cibertec.netTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.netTech.models.Product;
import com.cibertec.netTech.services.CategoryService;
import com.cibertec.netTech.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/edit/{productId}")
	public String showProductDetails(@PathVariable Long productId, Model model) {
		// Product product = productService.findById(productId).orElseThrow(() -> new
		// IllegalArgumentException("Invalid product Id:" + productId));
		Product product = productService.findById(productId).get();
		model.addAttribute("product", product);
		model.addAttribute("category", categoryService.findAll());
		return "product_detail";
	}

	@PostMapping("/guardar")
	public String guardar_producto(Product producto, Model model) {
		System.out.println(producto.toString());
		productService.save(producto);
		return "redirect:/dashboard";
	}

	@GetMapping("/delete/{id}")
	public String delere_producto(@PathVariable long id) {
		productService.deleteById(id);
		return "redirect:/dashboard";
	}
}
