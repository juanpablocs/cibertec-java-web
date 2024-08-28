package com.cibertec.netTech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.netTech.models.Category;
import com.cibertec.netTech.models.Product;
import com.cibertec.netTech.services.CategoryService;
import com.cibertec.netTech.services.ProductService;

@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String showIndex(Model model) {
		List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
		model.addAttribute("title", "Página de Inicio");
		return "index";
	}
	
	@GetMapping("/about")
	public String showAbout(Model model) {
		model.addAttribute("title", "Página About");
		return "about";
	}
	
	@GetMapping("/locations")
	public String showLocations(Model model) {
		model.addAttribute("title", "Página Locations");
		return "locations";
	}
	
	@GetMapping("/contact")
	public String showContact(Model model) {
		model.addAttribute("title", "Página Contact");
		return "contact";
	}
}
