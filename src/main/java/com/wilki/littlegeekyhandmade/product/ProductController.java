package com.wilki.littlegeekyhandmade.product;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;


    @GetMapping(value = "")
    public HttpEntity<?> getProducts(@RequestParam Optional<Integer> quantity) {
        Integer actualQuantity = quantity.orElseGet(() -> 0);
        ArrayList<ProductDto> productDtoArrayList =
                productMapper.productListToProductDtoList(productService.getProducts(actualQuantity));
        return new ResponseEntity<>(productDtoArrayList, HttpStatus.OK);
    }

    @GetMapping(value = "/{productId}")
    public HttpEntity<?> getProduct(@PathVariable Integer productId) {
        try {
            Product productById = productService.getProductById(Long.valueOf(productId));
            return new ResponseEntity<>(productMapper.productToProductDto(productById), HttpStatus.OK);
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

    @PostMapping("")
    public void addProduct(@RequestBody ProductDto productDto){
        Product product = productMapper.productDtoToProduct(productDto);
        log.info("Recieved Dto: " + product.toString());
        productService.addProduct(product);
    }

    @PutMapping("/{productId}")
    public HttpEntity<String> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer productId){
        try {
            Product product = productMapper.productDtoToProduct(productDto);
            productService.updateProduct(product, Long.valueOf(productId));
            return new ResponseEntity<>("Prod has been updated.", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
    }


}
