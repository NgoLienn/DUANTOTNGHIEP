package com.poly.Controller;

import com.poly.Entity.*;
import com.poly.Reponsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class PaymentController {
    @Autowired
    CartRepository cartRepo;

    @Autowired
    AccountReponsitory accountRepo;

    @Autowired
    CartItemsRepository cartItemsRepo;

    @Autowired
    OrdersReposiotry ordersRepo;

    @Autowired
    OrderItemRepository orderItemRepo;



    @GetMapping("/payment")
    public String ViewProfile(Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepo.findByUsername(username);
        model.addAttribute("account", account);
        Carts carts = cartRepo.findByCartUser(username);
        model.addAttribute("cart",carts);
        Long subtotal= cartItemsRepo.getSum(carts.getCartID());
        model.addAttribute("subtotal",subtotal);

        return "user/payment_method";
    }

    @PostMapping("/payment")
    public String payment(Model model, HttpServletRequest httpServletRequest, @RequestParam("payment") String payment){
            String username = httpServletRequest.getRemoteUser();
        if (payment.equals("true")) {
            Account account = accountRepo.findByUsername(username);
            Carts carts = cartRepo.findByCartUser(username);
            float subtotal= cartItemsRepo.getSum(carts.getCartID());
            Status status = new Status();
            status.setStatusID(1L);
            Orders orders = new Orders();
            orders.setAccount(account);
            orders.setStatus(status);
            orders.setDeliveryAddress(account.getAddress());
            orders.setPhone(account.getPhone());
            orders.setPaymentMethod("Thanh toán khi nhận hàng");
            orders.setTotalAmount(subtotal);
            ordersRepo.save(orders);


            for (Cart_Items cartItems : carts.getCart_items() ){
                Order_Items orderItems = new Order_Items();
                orderItems.setOrders(orders);
                orderItems.setSize_Product(cartItems.getSizeName());
                orderItems.setQuantity(cartItems.getQuantity());
                orderItems.setPrice(cartItems.getPrice());
                orderItems.setName(cartItems.getProductId().getName());
                orderItems.setSubtotal(cartItems.getSubtotal());
                orderItemRepo.save(orderItems);
                System.out.println(cartItems.getQuantity());
            }
            cartItemsRepo.deleteAll(carts.getCart_items());
            cartRepo.delete(carts);

        }
        return "redirect:/user/confirmation";
    }

}
