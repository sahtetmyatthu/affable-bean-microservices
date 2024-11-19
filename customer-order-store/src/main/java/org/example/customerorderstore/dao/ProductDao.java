package org.example.customerorderstore.dao;

import org.example.customerorderstore.dto.CustomerOrderInfo;
import org.example.customerorderstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {


}
