package com.cibertec.netTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.netTech.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
