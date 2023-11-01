package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProductController {

    @GetMapping("/abcd/")
    public String viewProduct(@PathVariable("viewid") Long productId, Model model) {

        return null;
    }

}
