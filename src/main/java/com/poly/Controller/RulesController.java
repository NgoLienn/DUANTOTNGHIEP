package com.poly.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RulesController {

    @GetMapping("/rules")
    public String ViewProfile(Model model, Authentication authentication) {

        return "user/rules";
    }
}
