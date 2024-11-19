package org.example.affablebeanui.ds;

import org.example.affablebeanui.dto.CartItem;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CartBean {
    private Set<CartItem> cartItems=
            new HashSet<>();

    public void clearCart(){
        cartItems.clear();
    }

    public void addToCart(CartItem cartItem){
        this.cartItems.add(cartItem);
    }
    public void addQuantity(List<Integer> quantities){
        int count=0;
        for(CartItem cartItem:cartItems){
            cartItem.setQuantity(quantities.get(count));
            count++;
        }
    }
    public int cartSize(){
        return cartItems.size();
    }

    public void deleteFromCart(int id){
        this.cartItems=cartItems
                .stream()
                .filter(item -> item.getId() != id)
                .collect(Collectors.toSet());
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }
}
