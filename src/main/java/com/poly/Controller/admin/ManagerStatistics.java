package com.poly.Controller.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudinary.provisioning.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import com.poly.Service.CategoryService;
import com.poly.Service.OrderItemService;
import com.poly.Service.OrderService;
import com.poly.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class ManagerStatistics {
    @Autowired
    CategoryService categoryService;

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    AccountReponsitory accountRepon;

    @GetMapping("/managerStatistics")
    public String ViewStatistics(Model model) {

        model.addAttribute("accountt", accountRepon.findAll());

        //Số thể loại
       model.addAttribute("a", categoryService.totalCategory());

        // Số người dùng
        model.addAttribute("totalAccount", accountService.totalAccount());

        // Số sản phẩm
        model.addAttribute("totalProducts", productService.totalProducts());

        // Tổng doanh thu
        model.addAttribute("totalAmountDateNow", orderService.totalAmountDateNow(new Date()));

        // Tổng doanh thu hiện lên biểu đồ
        model.addAttribute("data", orderService.reportRevenue(LocalDate.now().getYear()));

        // Thể loại sản phẩm
        model.addAttribute("categoryList", productService.countCategoryName());

        List<Object> list = orderItemService.reportTopProducts();
       
                // Top sản phẩm được bán nhiều nhất
        model.addAttribute("topProducts", list);

        return "admin/dashboard";

    }
}
