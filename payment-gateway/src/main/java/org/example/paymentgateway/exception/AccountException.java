package org.example.paymentgateway.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AccountException extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class,
    NameNotAdminException.class,AccountNotFoundError.class,
    InsufficientAmountError.class})
    public ResponseEntity<Object> handleException(Exception exception,
                                                  HttpServletRequest request
    )
        throws Exception{
        return handleExceptionInternal(
                exception,
                message(exception, HttpStatus.BAD_REQUEST.value()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                new ServletWebRequest(request)
        );
    }

    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatusCode status,
                                 WebRequest request) {
        return handleExceptionInternal(
                ex,
                message(ex, status.value()),
                headers,
                status,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        return handleExceptionInternal(ex,
                message(ex,status.value()),
                headers,
                status,
                request
                );
    }
    private ApiError message(Exception e,int statusCode){
        String errorMessage = e != null ? e.getMessage() : " Unknown Error!";
        String developerMessage = ExceptionUtils.getRootCauseMessage(e);
        return new ApiError(
                statusCode,
                errorMessage,
                developerMessage
        );
    }
}
