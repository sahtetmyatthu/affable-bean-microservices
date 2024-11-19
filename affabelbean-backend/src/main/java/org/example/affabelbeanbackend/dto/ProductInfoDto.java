package org.example.affabelbeanbackend.dto;

import java.time.LocalDateTime;

public record ProductInfoDto(
        int categoryId,
        String categoryName,
        int productId,
        String productName,
        double price,
        String description,
        LocalDateTime lastUpdate
) {

}
