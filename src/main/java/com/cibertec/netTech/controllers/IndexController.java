package com.cibertec.netTech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class IndexController {

	@GetMapping("/")
	public String showIndex(Model model) {
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
