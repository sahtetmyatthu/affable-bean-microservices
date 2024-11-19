package org.example.paymentgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
        private Integer id;
        private  String name;
        private String email;
        private String phoneNumber;
        private String address;
        private String cardNumber;
        private double amount;

}
