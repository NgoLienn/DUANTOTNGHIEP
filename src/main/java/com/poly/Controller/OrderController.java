package com.poly.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Entity.Account;
import com.poly.Entity.Order_Items;
import com.poly.Entity.Orders;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Entity.Size_Product;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.OrderItemRepository;
import com.poly.Reponsitory.OrdersReposiotry;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Reponsitory.SizeProductRepository;

@Controller
@RequestMapping("user")
public class OrderController {

    @Autowired
    OrderItemRepository orderItemRepo;

    @Autowired
    SizeProductRepository sizeProductRepo;

    @Autowired
    ReviewReponsitory reviewRepo;

    @Autowired
    AccountReponsitory accountRepo;

    @Autowired
    OrdersReposiotry ordersRepo;

    @GetMapping("/order")
    public String HistoryOrder(Model model, HttpServletRequest httpServlet) {

        Account account = accountRepo.findByUsername(httpServlet.getRemoteUser());

        List<Orders> orders = ordersRepo.finByUsernam(account.getUserName());
        model.addAttribute("orders", orders);

        List<Size_Product> sizeProducts = sizeProductRepo.findAll();
        model.addAttribute("sizeProducts", sizeProducts);
        return "user/order";
    }
}