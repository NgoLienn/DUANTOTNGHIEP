package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class confirm_password {
    @GetMapping("/confirm_password")
    public String ViewConfirmPass(Model model) {
        return "user/confirm_password";
    }
}
