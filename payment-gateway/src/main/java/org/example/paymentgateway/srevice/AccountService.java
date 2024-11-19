package org.example.paymentgateway.srevice;


import lombok.RequiredArgsConstructor;
import org.example.paymentgateway.dao.AccountDao;
import org.example.paymentgateway.dto.AccountDto;
import org.example.paymentgateway.dto.AccountRequestDto;
import org.example.paymentgateway.entity.Account;
import org.example.paymentgateway.exception.AccountNotFoundError;
import org.example.paymentgateway.exception.InsufficientAmountError;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;
    @Transactional
    public void makePayment(String creditCardNumber,double amount){
        Account cutomerAccount=accountDao
                .findByCreditCardNumber(creditCardNumber)
                .orElseThrow(AccountNotFoundError::new);
        Account ownerAccount=accountDao
                .findByCreditCardNumber("222222")
                .get();
        if(amount > cutomerAccount.getAmount()){
            throw new InsufficientAmountError();
        }
        cutomerAccount.setAmount(cutomerAccount.getAmount() - amount);
        ownerAccount.setAmount(ownerAccount.getAmount() + amount);
    }


    /*
    customerInfo,
    amounf
     */

    public AccountDto openAccount(AccountRequestDto accountDto){
        AccountDto accountDto1=toAccountDto(accountDto);
        accountDto1.setCardNumber(generateAccount());
        return toDto(accountDao
                .save(toEntity(accountDto1)));
    }
    private AccountDto toAccountDto(AccountRequestDto accountRequestDto){
        return new AccountDto(
                null,
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhoneNumber(),
                accountRequestDto.getAddress(),
                null,
                accountRequestDto.getAmount()
        );
    }
    private AccountDto toDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getName(),
                account.getEmail(),
                account.getPhoneNumber(),
                account.getAddress(),
                account.getCreditCardNumber(),
                account.getAmount()
        );
    }

    private Account toEntity(AccountDto accountDto){
        return new Account(
                accountDto.getName(),
                accountDto.getEmail(),
                accountDto.getPhoneNumber(),
                accountDto.getAddress(),
                accountDto.getCardNumber(),
                accountDto.getAmount()
        );
    }

    private String generateAccount(){
        SecureRandom random=new SecureRandom();
        // 99999 + 100000
        return "ASK"+ (random.nextInt(100000) + 100000);
    }

    /*
    openAccount
    payment
     */
}
