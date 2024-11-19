package org.example.affablebeanui.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String creditCardNumber;
}
