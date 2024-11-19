package org.example.paymentgateway.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class AccountNotFoundError extends ResponseStatusException {

    public AccountNotFoundError(){
        super(HttpStatusCode.valueOf(404),
                "Account Not Found!");
    }
}
