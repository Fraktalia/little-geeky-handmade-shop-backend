package com.wilki.littlegeekyhandmade.product;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {
    Product productDtoToProduct(ProductDto productDto);

    List<Product> productDtoListToProductList(List<ProductDto> productDtoList);

    ProductDto productToProductDto(Product product);
    
    List<ProductDto> productListToProductDtoList(List<Product> productList);
}
