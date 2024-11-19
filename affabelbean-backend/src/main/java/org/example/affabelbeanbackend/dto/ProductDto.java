package org.example.affabelbeanbackend.dto;

import java.time.LocalDateTime;

public record ProductDto(
        int id,
        String name,
        double price,
        String description,
        LocalDateTime lastUpdate,
        int categoryId
) {
}
