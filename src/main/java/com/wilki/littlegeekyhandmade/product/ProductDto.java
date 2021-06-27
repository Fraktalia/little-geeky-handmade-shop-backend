package com.wilki.littlegeekyhandmade.product;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productId;
    private String productName;
    private String productDescription;
    private int productPrice;
}
