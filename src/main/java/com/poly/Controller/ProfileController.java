package com.poly.Controller;


import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import com.poly.Service.CustomOAuth2User;
import com.poly.Service.UserServiceGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("user/profile")
public class ProfileController {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountReponsitory accountReponsitory;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserServiceGoogle userServiceGoogle;
    @GetMapping
    public String account(Model model,
                          Authentication authentication){
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();

        }
        Account account=accountService.findByUsername(users);
        model.addAttribute("account", new Account());
        model.addAttribute("profile", account);
        return "user/profile";
    }
    @PostMapping
    public String changepass(Model m,Authentication authentication,
        @ModelAttribute("account") Account account){
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();

        }
        Account acc =accountService.findByUsername(users);
        acc.setFullname(account.getFullname());
        acc.setPhone(account.getPhone());
        acc.setAddress(account.getAddress());
        accountReponsitory.save(acc);
        return "redirect:/user/profile";
    }
}