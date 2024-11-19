package org.example.affabelbeanbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductNotFoundError extends ResponseStatusException {
    public ProductNotFoundError(){
        super(HttpStatus.NOT_FOUND,"Product Not Found!");
    }
}
