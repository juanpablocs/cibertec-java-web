package com.cibertec.netTech.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MenuInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentURI = request.getRequestURI();
        String currentPage = currentURI.substring(currentURI.lastIndexOf('/') + 1);
        request.setAttribute("currentPage", currentPage.isEmpty() ? "" : currentPage);
        return true;
    }
}