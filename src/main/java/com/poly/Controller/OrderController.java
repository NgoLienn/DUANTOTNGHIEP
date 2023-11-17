package com.poly.Controller;

import java.util.List;
import java.util.Optional;

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
import com.poly.Entity.Status;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.OrderItemRepository;
import com.poly.Reponsitory.OrdersReposiotry;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Reponsitory.SizeProductRepository;
import com.poly.Reponsitory.StatusRepository;

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

    @Autowired
    StatusRepository statusRepo;

    @GetMapping("/order")
    public String HistoryOrder(Model model, HttpServletRequest httpServlet) {

        Account account = accountRepo.findByUsername(httpServlet.getRemoteUser());

        List<Orders> orders = ordersRepo.finByUsernam(account.getUserName());

        // Sắp xếp danh sách đơn hàng theo thời gian đặt hàng từ mới nhất đến cũ nhất
        orders.sort((order1, order2) -> order2.getOrderTime().compareTo(order1.getOrderTime()));

        model.addAttribute("orders", orders);

        List<Size_Product> sizeProducts = sizeProductRepo.findAll();
        model.addAttribute("sizeProducts", sizeProducts);
        return "user/order";
    }

    @PostMapping("/orderCancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đã hủy đơn" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findByStatusName("Đã hủy đơn");
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "Đã hủy đơn"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/user/order";
    }

}