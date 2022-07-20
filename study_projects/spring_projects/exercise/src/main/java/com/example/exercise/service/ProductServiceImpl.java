package com.example.exercise.service;

import com.example.exercise.model.Product;
import com.example.exercise.repository.ProductRepository;
import com.example.exercise.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("the product with id: "
                        .concat(productId.toString())
                        .concat(" no found")));
    }
}
