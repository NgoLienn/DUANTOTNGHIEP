package com.poly.Controller.admin;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import com.poly.Entity.Status;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.OrderItemRepository;
import com.poly.Reponsitory.OrdersReposiotry;
import com.poly.Reponsitory.StatusRepository;
import com.poly.Service.EmailService;
import com.poly.Service.OrderService;

@Controller
@RequestMapping("/admin")
public class ManagerOrder {

    @Autowired
    OrdersReposiotry ordersRepo;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderItemRepository orderItemRepo;

    @Autowired
    AccountReponsitory accountRepo;

    @Autowired
    StatusRepository statusRepo;

    @Autowired
    EmailService emailService;

    @GetMapping("/managerOrder")
    public String ViewOrder(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Orders> orderss;

        List<Status> status = statusRepo.findAll();
        model.addAttribute("status", status);

        // tìm kiếm sản phẩm
        if (query.equals("")) {
            orderss = ordersRepo.findAll();
        } else {
            orderss = orderService.searchOrders(query);
        }

        model.addAttribute("query", query);

        // Sắp xếp danh sách đơn hàng theo thời gian đặt hàng từ mới nhất đến cũ nhất
        orderss.sort((order1, order2) -> order2.getOrderTime().compareTo(order1.getOrderTime()));

        model.addAttribute("orders", orderss);

        return "admin/billing";
    }

    // đã xác nhận
    @PostMapping("/managerOrderSuccess/{orderId}")
    public String SuccessOrder(@PathVariable Long orderId, Model model,
            @RequestParam(value = "query", defaultValue = "") String query) {
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
                ordersRepo.save(order);

                Account userAccount = order.getAccount();
                if (userAccount != null) {
                    String recipientEmail = userAccount.getUserName();

                    // Gửi email thông báo hủy đơn hàng
                    emailService.sendCancellationEmail(recipientEmail,
                            "Lỗi kỹ thuật hoặc vấn đề hệ thống không cho phép xử lý đơn hàng. Mong quý khách thông cảm"); // Gọi phương thức gửi
                    // email
                }
            }
        }
        return "redirect:/admin/managerOrder";
    }

}
