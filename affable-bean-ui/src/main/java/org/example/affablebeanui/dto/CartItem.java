package org.example.affablebeanui.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CartItem {

    private int id;
    private String name;
    private double price;
    private String description;
    private LocalDateTime lastUpdate;
    private int categoryId;
    private int quantity;
    private List<Integer> cartItemQuantities=
            new ArrayList<>();


    public CartItem(int id, String name, double price, String description, LocalDateTime lastUpdate, int categoryId, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.lastUpdate = lastUpdate;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "price=" + price +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
