package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.poly.Entity.Categories;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;

@Controller
@RequestMapping("/")
public class MenuController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/menu")
    public String ViewMenuProducts(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
        // load toàn bộ sản phẩm
        List<Products> listProduct = productRepo.findAll();
        model.addAttribute("ListProduct", listProduct);

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        // phân trang sản phẩm
        // Page<Products> listpage = this.productService.getAll(pageNo);
        // model.addAttribute("ListProduct", listpage);
        // model.addAttribute("currentPage", pageNo);
        // model.addAttribute("totalPage", listpage.getTotalPages());

        return "user/menu";
    }
}
