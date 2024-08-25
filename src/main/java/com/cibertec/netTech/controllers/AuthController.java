package com.cibertec.netTech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cibertec.netTech.models.User;
import com.cibertec.netTech.services.AuthService;
import com.cibertec.netTech.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserService userService;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        boolean authSuccess = authService.authenticate(email, password, session);

        if (authSuccess) {
            return "redirect:/"; // Redirige al usuario a la página de inicio después del login
        } else {
            // La autenticación ha fallado. Maneja el caso, por ejemplo, mostrando un mensaje de error.
            return "login"; // Vuelve a la vista de login
        }
    }
	
	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("title", "Página de Login");
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegister(Model model) {
		model.addAttribute("title", "Página de Register");
		 model.addAttribute("user", new User());
		return "register";
	}
	

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
    	String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.create(user);
        // Redirigir al login después del registro exitoso
        return "redirect:/auth/login";
    }
}
