package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.poly.Entity.Categories;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepo;

    @GetMapping(value = { "/", "/user/index" })
    public String ViewProduct(Model model) {
        // load toàn bộ sản phẩm
        List<Products> listProduct = productRepo.findAll();
        model.addAttribute("ListProduct", listProduct);

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        return "user/index";
    }
}
