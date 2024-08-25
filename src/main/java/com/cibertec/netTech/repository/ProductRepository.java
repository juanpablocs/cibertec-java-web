package com.cibertec.netTech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.netTech.models.Product;

public interface ProductRepository  extends JpaRepository<Product, Long> {

}
