package org.example.customerorderstore;

import lombok.RequiredArgsConstructor;
import org.example.customerorderstore.dao.CustomerDao;
import org.example.customerorderstore.dao.ProductDao;
import org.example.customerorderstore.entity.Customer;
import org.example.customerorderstore.entity.Product;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@RequiredArgsConstructor
public class CustomerOrderStoreApplication {
    private final CustomerDao customerDao;
    private final ProductDao productDao;
    @Bean @Transactional @Profile("dev")
    public ApplicationRunner applicationRunner(){
        return r ->{
            Customer c1=
                    new Customer("John","john@gmail.com",
                            "12345","Mandalay");
            Product product1=new Product("Apple",23,2000);
            Product product2=new Product("Orange",20,1000);
            c1.addProduct(product1);
            c1.addProduct(product2);
            customerDao.save(c1);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CustomerOrderStoreApplication.class, args);
    }

}
