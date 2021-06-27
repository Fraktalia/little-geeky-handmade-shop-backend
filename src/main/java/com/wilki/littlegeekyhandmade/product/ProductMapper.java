package com.wilki.littlegeekyhandmade.product;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    public Product productDtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDescription(productDto.getProductDescription());
        return product;
    }

    public ProductDto productToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductDescription(product.getProductDescription());
        return productDto;
    }

    public ArrayList<ProductDto> productListToProductDtoList(List<Product> allProductsList) {
        ArrayList<ProductDto> productDtoArrayList = new ArrayList<>();
        for (Product product : allProductsList) {
            productDtoArrayList.add(productToProductDto(product));
        }
        return productDtoArrayList;
    }

//    public List<Product> productDtoListToProductList(List<ProductDto> productDtoList)

}
