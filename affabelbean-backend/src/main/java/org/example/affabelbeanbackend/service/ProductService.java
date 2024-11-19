package org.example.affabelbeanbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.affabelbeanbackend.dao.CategoryDao;
import org.example.affabelbeanbackend.dao.ProductDao;
import org.example.affabelbeanbackend.dto.CategoryDto;
import org.example.affabelbeanbackend.dto.ProductDto;
import org.example.affabelbeanbackend.dto.ProductInfoDto;
import org.example.affabelbeanbackend.entity.Category;
import org.example.affabelbeanbackend.entity.Product;
import org.example.affabelbeanbackend.exception.CategoryNotFoundError;
import org.example.affabelbeanbackend.exception.ProductNotFoundError;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    public List<ProductInfoDto> listAllProductInfo(){
        return productDao.listAllProductInfoDto();
    }

    public ProductDto findProductDtoById(int id){
        return productDao.findById(id)
                .map(this::productEntityToDto)
                .orElseThrow(ProductNotFoundError::new);
    }
    public List<ProductDto> findAllProducts(){
        return productDao.findAll()
                .stream()
                .map(this::productEntityToDto)
                .collect(Collectors.toList());
    }


    private CategoryDto findCategoryDtoById(int id){
        return categoryDao.findById(id)
                .map(this::categoryDtoToEntity)
                .orElseThrow(CategoryNotFoundError::new);
    }
    private CategoryDto categoryDtoToEntity(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    private ProductDto productEntityToDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getLastUpdate(),
                product.getCategory().getId()
        );
    }
}
