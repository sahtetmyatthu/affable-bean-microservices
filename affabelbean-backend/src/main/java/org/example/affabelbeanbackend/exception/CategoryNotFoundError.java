package org.example.affabelbeanbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFoundError extends ResponseStatusException {
    public CategoryNotFoundError(){
        super(HttpStatus.NOT_FOUND,
                "Category Not Found!");
    }
}
