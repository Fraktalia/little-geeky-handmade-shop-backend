package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String description;

    private int price; //TODO Check for best type; for now price in grosze

    @Override
    public String toString(){
        return String
                .format("Product[id=%d, name=%s, price=%d", id, productName, price);
    }
}
