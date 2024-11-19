package org.example.affablebeanui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientAmountError extends ResponseStatusException {
    public InsufficientAmountError(){
        super(HttpStatus.BAD_REQUEST,
                "Invalid Amount");
    }

}
