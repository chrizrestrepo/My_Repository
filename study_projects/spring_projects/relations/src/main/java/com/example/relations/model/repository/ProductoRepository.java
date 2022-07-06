package com.example.relations.model.repository;

import com.example.relations.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Product, Long> {
}
