package com.poly.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Carts;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;

@Controller
@RequestMapping("/user")
public class CartNullController {
    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/CartNull")
    public String ViewProfile(Model model, HttpServletRequest httpServletRequest) {
        // String username = httpServletRequest.getRemoteUser();
        // Carts carts = cartRepo.findByCartUser(username);
        // model.addAttribute("carts", carts);
        // Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        // model.addAttribute("subtotal", subtotal);
        return "user/cartNull";
    }
}
