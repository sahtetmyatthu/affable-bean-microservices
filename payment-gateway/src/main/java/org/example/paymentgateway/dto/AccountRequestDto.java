package org.example.paymentgateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
    @NotBlank(message = "Name cannot be blank!")
    private  String name;
    private String email;
    @JsonProperty("phone_number") private String phoneNumber;
    private String address;
    private double amount;
}
