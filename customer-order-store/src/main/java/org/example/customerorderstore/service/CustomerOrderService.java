package org.example.customerorderstore.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.customerorderstore.dao.CustomerDao;
import org.example.customerorderstore.dao.ProductDao;
import org.example.customerorderstore.dto.CustomerOrderInfo;
import org.example.customerorderstore.dto.ProductDto;
import org.example.customerorderstore.entity.Customer;
import org.example.customerorderstore.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private final CustomerDao customerDao;

    @Transactional
    public String saveCustomerOrderInfo(CustomerOrderInfo customerOrderInfo){
        Customer customer=toCustomerEntity(customerOrderInfo);
        List<Product> products=toProductEntity(customerOrderInfo);
        customer.setOrderNumber(generateProductOrderCode());
        for(Product product:products){
            customer.addProduct(product);
        }
        Customer managedCustomer=customerDao.save(customer);
        return managedCustomer.getOrderNumber();
    }
    private List<Product>
    toProductEntity(CustomerOrderInfo customerOrderInfo){
         return customerOrderInfo.getProducts().stream()
                 .map(p -> new Product(p.getName(),
                         p.getQuantity(),p.getPrice()))
                 .collect(Collectors.toList());
    }
    public CustomerOrderInfo findCustomerOrderInfoByOrderNumber(String orderNumber){
        Customer customer=customerDao.findByOrderNumber(orderNumber)
                .orElseThrow(EntityNotFoundException::new);


        return new CustomerOrderInfo(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getOrderNumber(),
                toProductDtoList(customer)
        );
    }

    public List<CustomerOrderInfo> listAllCustomerOrderInfo(){
        return customerDao.findAll().stream()
                .map(this::customerOrderInfoDto)
                .collect(Collectors.toList());
    }
    private CustomerOrderInfo customerOrderInfoDto(Customer customer){
        return new CustomerOrderInfo(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getOrderNumber(),
                toProductDtoList(customer)
        );
    }

    private List<ProductDto> toProductDtoList(Customer customer) {
        return customer.getProducts()
                .stream()
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }

    private ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    private Customer toCustomerEntity(CustomerOrderInfo customerOrderInfo){
        return new Customer(
                customerOrderInfo.getName(),
                customerOrderInfo.getEmail(),
                customerOrderInfo.getPhone(),
                customerOrderInfo.getAddress()
        );
    }
    private String generateProductOrderCode(){
        SecureRandom random=new SecureRandom();
        int num = random.nextInt(100000) + 100000;
        return String.valueOf(num);
    }
}
