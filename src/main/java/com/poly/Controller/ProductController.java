package com.poly.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ProductController {

    @GetMapping("/{productID}")
    public ModelAndView getProducts(@PathVariable long productID) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/products");
        return mv;
    }

}
