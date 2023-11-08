package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Categories;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;
import com.poly.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class ManagerProducts {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/managerProducts")
    public String ViewProfile(Model model) {
        List<Products> listProduct = productRepo.findAll();
        model.addAttribute("ListProduct", listProduct);

        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("admincategory", categoryList);

        return "admin/product";
    }

    @GetMapping("/managerProducts/edit/{productId}")
    public String edit(Model model, @PathVariable("productId") int productId) {
        List<Products> listProduct = productRepo.findAll();
        model.addAttribute("ListProduct", listProduct);

        Products adminproduct = productService.getProductById(productId);
        model.addAttribute("adminproduct", adminproduct);

        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("admincategory", categoryList);

        return "admin/product";
    }
}
