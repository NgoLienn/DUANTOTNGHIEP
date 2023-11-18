package com.poly.Controller;


import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import com.poly.Service.CustomOAuth2User;
import com.poly.Service.UserServiceGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @RequestMapping()
    public String account(HttpServletRequest request, OAuth2AuthenticationToken token,Model model){
        String username=request.getRemoteUser();
        Account account=accountService.findByUsername(username);
        model.addAttribute("account", new Account());
        model.addAttribute("profile", account);
        OAuth2User user = token.getPrincipal();
        String email = user.getAttribute("email");
        return "user/profile";
    }
    @PostMapping()
    public String changepass(Model m,HttpServletRequest request,
        @ModelAttribute("account") Account account){
        String username=request.getRemoteUser();
        Account acc =accountService.findByUsername(username);
        acc.setFullname(account.getFullname());
        acc.setPhone(account.getPhone());
        acc.setAddress(account.getAddress());
        accountReponsitory.save(acc);
        return "redirect:/user/profile";

    }
}