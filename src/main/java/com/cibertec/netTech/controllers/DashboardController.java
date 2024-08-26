package com.cibertec.netTech.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cibertec.netTech.models.Category;
import com.cibertec.netTech.models.Product;
import com.cibertec.netTech.models.User;
import com.cibertec.netTech.services.CategoryService;
import com.cibertec.netTech.services.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session; // Asumiendo que tienes acceso a HttpSession

	@GetMapping("")
	public String showDashboard(Model model) {
		model.addAttribute("title", "Página Dashboard");

		Long userId = (Long) session.getAttribute("userId"); // Asume que el ID del usuario está almacenado en la sesión
		List<Product> products = productService.findAll();
		List<Category> categories = categoryService.findAll();
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		model.addAttribute("totalProducts", productService.total());
		model.addAttribute("totalCategories", categoryService.total());
		model.addAttribute("totalSales", "S/50,000.00");
		return "dashboard";
	}

	@GetMapping("/products")
	public String showProducts(Model model) {
		model.addAttribute("title", "Página Dashboard");

		List<Product> products = productService.findAll();
		model.addAttribute("products", products);

		return "dashboard";
	}

	@GetMapping("/products/create")
	public String showProductsCreate(Model model) {
		model.addAttribute("title", "Página Form Producto");

		Long userId = (Long) session.getAttribute("userId"); // Asume que el ID del usuario está almacenado en la sesión
		model.addAttribute("categories", categoryService.findAll());

		return "dashboard_products_form";
	}

	@GetMapping("/categories/create")
	public String showCategoriesCreate(Model model) {
		model.addAttribute("title", "Página Form Category");
		return "dashboard_category_form";
	}

	@PostMapping("/categories/create")
	public String categoriesCreate(@RequestParam("name") String name) {
		Category c = new Category();
		c.setName(name);
		categoryService.save(c);
		return "redirect:/dashboard";
	}

	@PostMapping("/products/create")
	public String createProduct(@RequestParam("name") String name, @RequestParam("price") Integer price,
			@RequestParam("image") MultipartFile image, @RequestParam("description") String description,
			@RequestParam("category") Long categoryId, HttpSession session) throws IOException {
		Long userId = (Long) session.getAttribute("userId");
		Optional<Category> category = categoryService.findById(categoryId);

		String imageName = productService.saveImage(image);

		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setPhoto(imageName);
		product.setDescription(description);
		product.setCategory(category.get());

		productService.save(product); // Asumiendo que tu servicio puede manejar la carga de imágenes

		return "redirect:/dashboard";
	}

}
