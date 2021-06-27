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
@Entity // Tworzy się tabela, na podstawie klasy (w tym przypadku Product).
        // Każdy rekord tabeli jest (jeżeli istnieje) obiektem klasy Product.
@Table(name = "products") // Zmiana nazwy tabeli na "products", bo przez @entity
                        // i tak tworzy się tabela, tylko, że nazywa się "Product"
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private int productPrice; //TODO Check for best type; for now price in grosze

    @Override //W Javie wszystkie klasy dziedziczą po Object. ToString jest metodą klasy Object, więc
            //zawsze jest override'owana
    public String toString(){
        return String
                .format("Product[id=%d, name=%s, price=%d", productId, productName, productPrice);
    }
}
