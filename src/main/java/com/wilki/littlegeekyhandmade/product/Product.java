package com.wilki.littlegeekyhandmade.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private int price; //TODO Check for best type; for now price in grosze

    @Override
    public String toString(){
        return String.format("Product[id=%d, name=%s, price=%d", id, name, price);
    }
}
