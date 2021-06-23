package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Long id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
