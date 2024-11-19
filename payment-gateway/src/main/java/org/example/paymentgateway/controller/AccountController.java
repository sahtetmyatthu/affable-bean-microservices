package org.example.paymentgateway.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.paymentgateway.dto.AccountDto;
import org.example.paymentgateway.dto.AccountRequestDto;
import org.example.paymentgateway.exception.NameNotAdminException;
import org.example.paymentgateway.srevice.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class AccountController {
    private final AccountService accountService;
    /*
    payment - fromId,toId,amount
     */
    record PaymentRequest(
            @JsonProperty("credit_card_number") String creditCardNumber,
            double amount
    ){}
    @PostMapping("/make-payment")
    public ResponseEntity makePayment(@RequestBody PaymentRequest request){

        accountService.makePayment(request.creditCardNumber,request.amount);
        return ResponseEntity.ok("success payment");
    }
    @PostMapping("/open-account")
    public AccountDto openAccount(@RequestBody @Valid AccountRequestDto accountRequestDto){
        if(accountRequestDto.getName().equalsIgnoreCase("admin")){
            throw new NameNotAdminException();
        }
        return accountService.openAccount(accountRequestDto);
    }

}
