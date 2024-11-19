package org.example.paymentgateway;

import lombok.RequiredArgsConstructor;
import org.example.paymentgateway.dao.AccountDao;
import org.example.paymentgateway.entity.Account;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

/*
entity
account
-customer info
-account no
-balance
 */
@SpringBootApplication
@RequiredArgsConstructor
public class PaymentGatewayApplication {
    private final AccountDao accountDao;

    @Bean @Profile("dev")
    public ApplicationRunner runner(){
        return r ->{
            Account account=
                    new Account("John Doe","john@gmail.com","55-555-55","Yangon","222222",1000);
            accountDao.save(account);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentGatewayApplication.class, args);
    }

}
