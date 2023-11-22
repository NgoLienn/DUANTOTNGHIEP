package com.poly.Controller.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Account;
import com.poly.Entity.Order_Items;
import com.poly.Entity.Orders;
import com.poly.Entity.Status;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.OrderItemRepository;
import com.poly.Reponsitory.OrdersReposiotry;
import com.poly.Reponsitory.StatusRepository;

@Controller
@RequestMapping("/admin")
public class ManagerOrder {

    @Autowired
    OrdersReposiotry ordersRepo;

    @Autowired
    OrderItemRepository orderItemRepo;

    @Autowired
    AccountReponsitory accountRepo;

    @Autowired
    StatusRepository statusRepo;

    @GetMapping("/managerOrder")
    public String ViewOrder(Model model) {

        List<Orders> orderss = ordersRepo.findAll();

        // Sắp xếp danh sách đơn hàng theo thời gian đặt hàng từ mới nhất đến cũ nhất
        orderss.sort((order1, order2) -> order2.getOrderTime().compareTo(order1.getOrderTime()));

        model.addAttribute("orders", orderss);

        List<Status> status = statusRepo.findAll();
        model.addAttribute("status", status);

        return "admin/billing";
    }

    // đã xác nhận
    @PostMapping("/managerOrderSuccess/{orderId}")
    public String SuccessOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đã Xác Nhận" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(2L).orElse(null);
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "Đã Xác Nhận"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/admin/managerOrder";
    }

    // đang giao hàng
    @PostMapping("/managerOrderDelivering/{orderId}")
    public String deliveringOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đang giao hàng" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(3L).orElse(null);
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "đang giao hàng"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/admin/managerOrder";
    }

    // đã giao hàng
    @PostMapping("/managerOrderDelivered/{orderId}")
    public String deliveredOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đẫ giao hàng" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(4L).orElse(null);
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "đẫ giao hàng"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/admin/managerOrder";
    }

    // đã hủy
    @PostMapping("/managerOrderCancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đã hủy đơn" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(6L).orElse(null);
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "Đã hủy đơn"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/admin/managerOrder";
    }

}
