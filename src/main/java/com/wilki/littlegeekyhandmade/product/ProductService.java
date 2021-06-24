package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductById(Long id) throws NoSuchElementException{
        Optional<Product> maybeProduct = productRepository.findById(id);
        if(maybeProduct.isPresent()){
            return maybeProduct.get();
        }else {
            throw new NoSuchElementException("Product not found.");
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean deleteProductById(Long productId) {
        try {
            productRepository.deleteById(productId);
            return true;
        }catch (EmptyResultDataAccessException e)
        {
            return false;
        }

    }

    public void addProduct(Integer productId) {
    }
}
