package org.example.affabelbeanbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.affabelbeanbackend.dao.ProductDao;
import org.example.affabelbeanbackend.dto.ProductDto;
import org.example.affabelbeanbackend.dto.ProductInfoDto;
import org.example.affabelbeanbackend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/product")
    public ProductDto findProductById(@RequestParam("id")int id){
        return productService.findProductDtoById(id);
    }
    @GetMapping("/list-product-info")
    public List<ProductInfoDto> findAllProductInfo(){
        return productService.listAllProductInfo();
    }
}
