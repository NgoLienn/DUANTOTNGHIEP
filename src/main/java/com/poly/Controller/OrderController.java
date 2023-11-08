package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class OrderController {

    @GetMapping("/order")
    public String HistoryOrder(Model model) {
        return "user/order";
    }
}