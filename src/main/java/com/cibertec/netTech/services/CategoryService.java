package com.cibertec.netTech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.netTech.models.Category;
import com.cibertec.netTech.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> findAll() {
        return categoryRepository.findAll();
    }
	
	public void deleteById(long id) {
		categoryRepository.deleteById(id);
	}
	
	public Long total() {
		return categoryRepository.count();
	}

	
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}
	
	public Category save(Category p) {
        return categoryRepository.save(p);
    }
}
