package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class WaitConfirmationController {
    @GetMapping("/confirmation")
    public String ViewProfile(Model model) {
        return "user/wait_confirmation";
    }
}
