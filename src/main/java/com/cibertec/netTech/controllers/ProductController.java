package com.cibertec.netTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.netTech.models.Product;
import com.cibertec.netTech.services.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String showProductDetails(@PathVariable Long productId, Model model) {
        Product product = productService.findById(productId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));
        model.addAttribute("product", product);
        model.addAttribute("category", product.getCategory());
        return "product_detail";
    }
}
