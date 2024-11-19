package org.example.affablebeanui.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.affablebeanui.ds.CartBean;
import org.example.affablebeanui.dto.CartItem;
import org.example.affablebeanui.dto.CustomerDto;
import org.example.affablebeanui.dto.ProductDto;
import org.example.affablebeanui.dto.ProductInfoDto;
import org.example.affablebeanui.store.dto.CustomerOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AffableBeanService {
    private RestClient restClient;
    private List<ProductInfoDto> productList;
    @Autowired
    private CartBean cartBean;

    public String saveCustomerOrderInfo(CustomerDto customerDto){
        List<org.example.affablebeanui.store.dto.ProductDto>
                productDtoList = listCartItems()
                .stream()
                .map(this::productDto)
                .collect(Collectors.toList());
        CustomerOrderInfo customerOrderInfo=
                new CustomerOrderInfo(
                        null,
                        customerDto.getName(),
                        customerDto.getEmail(),
                        customerDto.getPhoneNumber(),
                        customerDto.getAddress(),
                        null,
                        productDtoList
                );

        restClient=RestClient.builder()
                .baseUrl("http://localhost:8080/customer/store")
                .build();

       return restClient.post()
                .uri("/customer-order-save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerOrderInfo)
                .retrieve()
                .body(String.class);



    }
    private org.example.affablebeanui.store.dto.ProductDto
    productDto(CartItem item){
        return new org.example.affablebeanui.store.dto.ProductDto(
                null,
                item.getName(),
                item.getQuantity(),
                item.getPrice()
        );
    }
    public CustomerOrderInfo getCustomerOrderInfoByOrderNumber(String orderNumber){
        restClient = RestClient.builder().baseUrl("http://localhost:8080/customer/store")
                .build();

        return restClient.get()
                .uri("/customer-order-info/{id}",orderNumber)
                .retrieve()
                .body(CustomerOrderInfo.class);
    }

    record PaymentRequest(
            @JsonProperty("credit_card_number") String creditCardNumber,
            double amount
    ){

    }
    public String checkout(CustomerDto customerDto, double amount){
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/payment")
                .build();

         return restClient.post()
                .uri("/make-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new PaymentRequest(customerDto
                        .getCreditCardNumber(),
                        amount))
                .retrieve()
                .body(String.class);
    }


    public void deleteFromCart(int id){
        cartBean.deleteFromCart(id);
    }

    public void addQuantity(List<Integer> list){
        cartBean.addQuantity(list);
    }

    public void clearCart(){
        cartBean.clearCart();
    }

    public Set<CartItem> listCartItems(){
        return cartBean.getCartItems();
    }

    public void addToCart(int productID){
        cartBean.addToCart(toCartItem(findProductDtoById(productID)));
    }
    public int cartSize(){
        return cartBean.cartSize();
    }

    public AffableBeanService(){
        productList=new ArrayList<>();
        restClient= RestClient.builder()
                .baseUrl("http://localhost:8080/products")
                .build();
        this.productList = listProducts();
    }
    public List<ProductInfoDto> listProducts(){
        return restClient.get()
                .uri("/list-product-info")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProductInfoDto>>() {
                });
    }

    private CartItem toCartItem(ProductDto productDto){
        return new CartItem(
                productDto.id(),
                productDto.name(),
                productDto.price(),
                productDto.description(),
                productDto.lastUpdate(),
                productDto.categoryId(),
                1
        );
    }

    public ProductDto findProductDtoById(int id){
        return productList.stream()
                .filter(p -> p.productId() == id)
                .map(this::toProductDto)
                .findFirst()
                .get();
    }

    public List<ProductDto> listProductsByCategory(int categoryId){
        return this.productList
                .stream()
                .filter(p -> p.categoryId() == categoryId)
                .map(this::toProductDto)
                .collect(Collectors.toList());
    }


    private ProductDto toProductDto(ProductInfoDto productInfoDto){
        return new ProductDto(
                productInfoDto.productId(),
                productInfoDto.productName(),
                productInfoDto.price(),
                productInfoDto.description(),
                productInfoDto.lastUpdate(),
                productInfoDto.categoryId()
        );
    }
}
