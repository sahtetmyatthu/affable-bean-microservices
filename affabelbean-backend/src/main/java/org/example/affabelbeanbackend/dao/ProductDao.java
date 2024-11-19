package org.example.affabelbeanbackend.dao;

import org.example.affabelbeanbackend.dto.ProductInfoDto;
import org.example.affabelbeanbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {


    @Query("""
select new org.example.affabelbeanbackend.dto.ProductInfoDto(
p.category.id,p.category.name,p.id,p.name,p.price,p.description,p.lastUpdate
) from Product  p
""")
    public List<ProductInfoDto> listAllProductInfoDto();

}
