package com.poly.Controller;


import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @RequestMapping()
    public String account(HttpServletRequest request, Model model){
        String username=request.getRemoteUser();
        Account account=accountService.findByUsername(username);
        model.addAttribute("account", new Account());
        model.addAttribute("profile", account);
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