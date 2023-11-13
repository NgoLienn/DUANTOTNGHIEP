package com.poly.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ManagerAccount {
    @GetMapping("/managerAccount")
    public String ViewAccount(Model model) {
        return "admin/tables";
    }
}
