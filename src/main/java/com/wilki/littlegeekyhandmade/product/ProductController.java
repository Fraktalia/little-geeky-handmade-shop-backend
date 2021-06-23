package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Product getProduct(@PathVariable Integer productId) {
        return productService.getProductById(Long.valueOf(productId));
    }

}
