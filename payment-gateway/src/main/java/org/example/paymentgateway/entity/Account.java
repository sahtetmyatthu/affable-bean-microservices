package org.example.paymentgateway.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @Column(unique = true)
    private String creditCardNumber;
    private double amount;

    public Account(String name, String email, String phoneNumber, String address, String creditCardNumber, double amount) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.amount = amount;
    }
}
