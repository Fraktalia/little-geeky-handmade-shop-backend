package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{productId}")
    public HttpEntity<?> getProduct(@PathVariable Integer productId) {
        try {
            Product productById = productService.getProductById(Long.valueOf(productId));
            return new ResponseEntity<>(productById, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public HttpEntity<String> deleteProduct(@PathVariable Integer productId){
        if(productService.deleteProductById(Long.valueOf(productId))){
            return new ResponseEntity<>("Product has been removed", HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{productId}")
    public void addProduct(@PathVariable Integer productId){
        productService.addProduct(productId);
    }


}
