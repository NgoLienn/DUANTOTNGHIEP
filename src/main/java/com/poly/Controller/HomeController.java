package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.poly.Dao.CategoryDao;
import com.poly.Dao.ProductDao;
import com.poly.Entity.Products;
import com.poly.Reponsitory.CategoryRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;
import com.poly.Service.ProductService;

@Controller
public class HomeController {

    // @Autowired
    // private ProductService productservice;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = { "/", "/user/index" })
    public String ViewProduct(Model model) {
        List<Products> listProduct = productDao.findAll();
        model.addAttribute("ListProduct", listProduct);

        return "user/index";
    }

    // @RequestMapping("/{categoryId}")
    // public ModelAndView Category(@PathVariable String categoryId) {
    // ModelAndView mv = new ModelAndView("user/ahi");
    // mv.addObject("Id", categoryId);
    // // Categories category = new Categories();
    // // category.setId(categoryId);
    // // mv.addObject("products", productservice.getProductByCategoryId(category));
    // return mv;
    // }

    // @GetMapping("/category")
    // public ModelAndView showProductByCategory() {
    // ModelAndView mv = new ModelAndView("user/ahi");
    // mv.addObject("category", productservice.getDataCategorys());
    // return mv;
    // }
}
