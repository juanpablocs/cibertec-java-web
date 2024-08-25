package com.cibertec.netTech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cibertec.netTech.interceptors.AuthInterceptor;
import com.cibertec.netTech.interceptors.MenuInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(new AuthInterceptor())
        .addPathPatterns("/auth/**", "/admin", "/dashboard", "/dashboard/**") // Aplicar a todas las rutas
        .excludePathPatterns("/css/**", "/icons/**", "/images/**"); // Excluir
        registry.addInterceptor(new MenuInterceptor());
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:" + env.getProperty("app.upload.dir")+ "/");
    }
}