package org.example.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameNotAdminException extends ResponseStatusException {
    public NameNotAdminException(){
        super(HttpStatus.BAD_REQUEST,"Name cannot be admin!");
    }
}
