package org.example.paymentgateway.dao;

import org.example.paymentgateway.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDao extends JpaRepository<Account,Integer> {
    Optional<Account>
    findByCreditCardNumber(String creditCardNumber);
}
