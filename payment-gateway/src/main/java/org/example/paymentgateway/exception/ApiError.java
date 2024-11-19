package org.example.paymentgateway.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiError {
    private int statusCode;
    private String errorMessage;
    private String developerErrorMessage;
}
