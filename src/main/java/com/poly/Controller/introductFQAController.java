package com.poly.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Carts;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;

@Controller
@RequestMapping("/user")
public class introductFQAController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/introductFQAController")
    public String ViewContact(Model model, Authentication authentication) {

        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Carts carts = cartRepo.findByCartUser(users);
        if (carts == null) {
            return "redirect:/user/CartNull";
        } else {
            Long subtotal = cartItemsRepo.getSum(carts.getCartID());
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("carts", carts);
        }

        return "user/introductFQA";
    }
}
