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

    public List<Product> getProducts(Integer quantity) {
        List<Product> allProducts = getAllProducts();
        if (quantity == 0) {
            return allProducts;
        } else {
            return allProducts.subList(0, quantity);
        }
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

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product, Long productId) {
        Product updateProduct = productRepository.findById(productId).get();
        updateProduct.setProductName(product.getProductName());
        updateProduct.setProductDescription(product.getProductDescription());
        updateProduct.setProductPrice(product.getProductPrice());
        productRepository.save(updateProduct);
    }
}
