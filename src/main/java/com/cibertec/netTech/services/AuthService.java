package com.cibertec.netTech.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cibertec.netTech.models.User;
import com.cibertec.netTech.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Autenticación exitosa, guardar información del usuario en la sesión
                session.setAttribute("userFullName", user.getFullName());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userId", user.getId());
                return true; // Indica éxito
            }
        }
        return false; // Autenticación fallida
    }
}
