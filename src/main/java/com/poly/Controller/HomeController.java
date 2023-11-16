package com.poly.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.poly.Entity.Carts;
import com.poly.Entity.Categories;
import com.poly.Entity.Products;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping(value = { "/", "/user/index" })
    public String ViewProduct(Model model, HttpServletRequest httpServletRequest) {
        // load toàn bộ sản phẩm
        List<Products> listProduct = productRepo.findAll();
        model.addAttribute("ListProduct", listProduct);

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        // String username = httpServletRequest.getRemoteUser();
        // Carts carts = cartRepo.findByCartUser(username);
        // model.addAttribute("carts", carts);
        // Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        // model.addAttribute("subtotal", subtotal);

        return "user/index";
    }
}
