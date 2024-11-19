package org.example.customerorderstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String orderNumber;


    @OneToMany(mappedBy = "customer",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true)
    private List<Product> products=
            new ArrayList<>();

    public void addProduct(Product product){
        product.setCustomer(this);
        products.add(product);
    }

    public Customer(String name, String email, String phone, String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }



}
