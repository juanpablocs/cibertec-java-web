package com.cibertec.netTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.netTech.models.Category;
import com.cibertec.netTech.models.Product;
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
	public String show(@PathVariable Long categoryId, Model model) {
	    Category c = categoryService.findById(categoryId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + categoryId));
	    model.addAttribute("title", "Productos de la Tienda");
	    model.addAttribute("products", c.getProducts());
	    return "category_products";
	}
	
	@GetMapping("/edit/{id}")
	public String showDetails(@PathVariable Long id, Model model) {
		Category c = categoryService.findById(id).get();
		model.addAttribute("category", c);
		return "category_detail";
	}
	
	@PostMapping("/guardar")
	public String guardar(Category c, Model model) {
		System.out.println(c.toString());
		categoryService.save(c);
		return "redirect:/dashboard";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		categoryService.deleteById(id);
		return "redirect:/dashboard";
	}
}
