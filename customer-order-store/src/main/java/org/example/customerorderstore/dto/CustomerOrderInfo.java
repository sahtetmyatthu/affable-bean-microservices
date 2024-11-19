package org.example.customerorderstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerOrderInfo {
    /*
    [
    {
    "id":null,
    "name":"John",
    "email":"john@gmail.com",
    "phone":"12345",
    "address":"Mandalay",
    "orderNumber":"123344",
    "products":[
    {
    },
    {
    },
    {
    }
    ]
    }
    ]
     */
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String orderNumber;
    private List<ProductDto> products=
            new ArrayList<>();
}
