package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;



@Controller
@RequestMapping("/user")
public class ProductDetailController {

//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    ImageProductService imageProductService;
//
//    @GetMapping("/productDetail/{iddetail}")
//    public String getProducts(@PathVariable("iddetail") Long iddetail, Model model) {
//        Products produc = productService.getProductById(iddetail);
//        model.addAttribute("product", produc);
//
//        List<Image_product> images = imageProductService.getImagesByProductId(iddetail);
//        model.addAttribute("images", images);
//        return "user/product_detail";
//    }
}
