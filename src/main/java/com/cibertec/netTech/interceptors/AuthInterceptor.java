package com.cibertec.netTech.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // Obtener la sesión si existe, no crear una nueva
        String loginURI = request.getContextPath() + "/auth/login";
        String registerURI = request.getContextPath() + "/auth/register";
        String requestURI = request.getRequestURI();

        boolean loggedIn = session != null && session.getAttribute("userId") != null;
        boolean authRequest = requestURI.startsWith(request.getContextPath() + "/auth/");

        if (loggedIn && authRequest) {
            // Usuario ya está logueado y trata de acceder a /auth/*, redirigir a home
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return false; // No continuar con la cadena de interceptores
        } else if (!loggedIn && (requestURI.equals(loginURI) || requestURI.equals(registerURI))) {
            // Permitir accesos a /auth/login y /auth/register si no está logueado
            return true; // Continuar con la cadena de interceptores
        } else if (loggedIn) {
            // Usuario logueado accediendo a rutas fuera de /auth/*
            return true; // Continuar con la cadena de interceptores
        } else {
            // Usuario no logueado tratando de acceder a rutas fuera de /auth/*, redirigir a login
            response.sendRedirect(loginURI);
            return false; // No continuar con la cadena de interceptores
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Opcional: manipular la solicitud después de que el controlador la haya manejado
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Opcional: ejecutar código después de completar la solicitud y la respuesta
    }
}
