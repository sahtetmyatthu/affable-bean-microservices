package org.example.customerorderstore.dao;

import org.example.customerorderstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByOrderNumber(String orderNumber);
}
