package com.cibertec.netTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.netTech.models.Category;
import com.cibertec.netTech.services.CategoryService;

@Controller
@RequestMapping("/categorias")
public class CategoryController {
	
	@Autowired
    private CategoryService categoryService;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("title", "Página Categorias");
		model.addAttribute("categories", categoryService.findAll());
		return "category";
	}

	@GetMapping("/{categoryId}")
	public String showProducts(@PathVariable Long categoryId, Model model) {
	    Category c = categoryService.findById(categoryId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + categoryId));
	    model.addAttribute("title", "Productos de la Tienda");
	    model.addAttribute("products", c.getProducts());
	    return "category_products";
	}
}
