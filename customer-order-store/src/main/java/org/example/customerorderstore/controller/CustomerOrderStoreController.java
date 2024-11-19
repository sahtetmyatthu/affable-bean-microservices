package org.example.customerorderstore.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.customerorderstore.dto.CustomerOrderInfo;
import org.example.customerorderstore.service.CustomerOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer/store")
public class CustomerOrderStoreController {
    private final CustomerOrderService customerOrderService;

    @GetMapping("/customer-order-info-lists")
    public List<CustomerOrderInfo> listAllCustomerOrderInfo(){
        return customerOrderService.listAllCustomerOrderInfo();
    }
    @GetMapping("/customer-order-info/{id}")
    public CustomerOrderInfo
    findCustomerOrderInfoByOrderNumber(@PathVariable("id") String orderNumber){
        return customerOrderService
                .findCustomerOrderInfoByOrderNumber(orderNumber);
    }


    @PostMapping("/customer-order-save")
    public ResponseEntity<String> saveCustomerOrder(@RequestBody
                                                    CustomerOrderInfo info){
        String orderNumber=customerOrderService.saveCustomerOrderInfo(info);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderNumber);
    }
}
