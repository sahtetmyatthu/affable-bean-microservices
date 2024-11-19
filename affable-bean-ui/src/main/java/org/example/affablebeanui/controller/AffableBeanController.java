package org.example.affablebeanui.controller;

import lombok.RequiredArgsConstructor;
import org.example.affablebeanui.dto.CartItem;
import org.example.affablebeanui.dto.CustomerDto;
import org.example.affablebeanui.exception.InsufficientAmountError;
import org.example.affablebeanui.service.AffableBeanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/affable-bean/client")
@Controller
@RequiredArgsConstructor
public class AffableBeanController {
    private Logger logger= LoggerFactory
            .getLogger(this.getClass().getSimpleName());
    private final AffableBeanService affableBeanService;
    @GetMapping({"/","/home"})
    public String home(){
        return "home";
    }
    @RequestMapping("/checkout/view")
    public String makePayment(Model model){
        model.addAttribute("customer",new CustomerDto());
        model.addAttribute("successPayment",model
                .containsAttribute("success"));
        return "checkoutView";
    }
    //account number : ASK117485
    @PostMapping("/checkout/customer")
    public String processCustomer(CustomerDto customerDto
            ,@ModelAttribute("totalPrice")double amount,
                                  RedirectAttributes attributes){
        String code = null;
        try {
            code =affableBeanService
                    .checkout(customerDto,(amount+3));
            System.out.println("Code:"+ code);
            String success=affableBeanService.saveCustomerOrderInfo(customerDto);
            System.out.println(success);
            this.orderNumber = success;
            return "redirect:/affable-bean/client/checkout/success";
        }catch (Exception e){
            attributes.addFlashAttribute("failure"
                    , true);
            return "redirect:/affable-bean/client/checkout/view";
        }

    }
    private String orderNumber;

    @RequestMapping("/checkout/success")
    public String checkoutSuccess(Model model){
        double totalPrice = affableBeanService
                .getCustomerOrderInfoByOrderNumber(orderNumber)
                        .getProducts()
                                .stream()
                                        .map(c -> c.getPrice() * c.getQuantity())
                                                .mapToDouble(Double::doubleValue)
                                                        .sum();


        model.addAttribute("customerOrderInfo",
                affableBeanService
                        .getCustomerOrderInfoByOrderNumber(orderNumber));
        model.addAttribute("totalOrderPrice",
                totalPrice);
        return "success";
    }
    @PostMapping("/checkout")
    public String checkout(CartItem cartItem,
                           RedirectAttributes redirectAttributes){
        System.out.println(cartItem.getCartItemQuantities());
        affableBeanService.addQuantity(cartItem.getCartItemQuantities());
        System.out.println(affableBeanService.listCartItems());
        redirectAttributes.addFlashAttribute("render",true);
        return "redirect:/affable-bean/client/view-cart";
    }
    @GetMapping("/cart/clear")
    public String clearCart(){
        affableBeanService.clearCart();
        return "redirect:/affable-bean/client/view-cart";
    }

    @RequestMapping("/view-cart")
    public String viewCart(Model model){
        model.addAttribute("cartItems",affableBeanService
                .listCartItems());
        model.addAttribute("cartItem",new CartItem());
        model.addAttribute("render",model.containsAttribute("render"));
        return "cartView";
    }
    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("id")int id,
                                 @RequestParam("cid")int cid){
        affableBeanService.deleteFromCart(id);
        return "redirect:/affable-bean/client/view-cart";
    }
    @GetMapping("/add-to-cart")
    public String attToCart(@RequestParam("id")int id,
                            @RequestParam("cid")int cid){
        affableBeanService.addToCart(id);
        return "redirect:/affable-bean/client/product?id="+cid;
    }
    @GetMapping("/product")
    public String listProductsByCategory(@RequestParam("id")int categoryId,
                                         Model model){
        model.addAttribute("products",
                affableBeanService.listProductsByCategory(categoryId));
        return "product";
    }
    @ModelAttribute("cartSize")
    public int cartSize(){
        return affableBeanService.cartSize();
    }
    @ModelAttribute("totalPrice")
    public Double totalPrice(){
        double total= affableBeanService.listCartItems()
                .stream()
                .map( item -> item.getPrice() * item.getQuantity())
                .mapToDouble(Double::doubleValue)
                .sum();
        return Double.parseDouble("%.2f".formatted(total));
    }
}
