package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ForgotController {
    @GetMapping("/forgot_password")
    public String ViewProfile(Model model) {
        return "user/forgot_pass";
    }
}
