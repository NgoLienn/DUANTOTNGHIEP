package com.poly.Controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.poly.Entity.Orders;
import com.poly.Reponsitory.OrdersReposiotry;

@Controller
public class InvoiceController {
    @Autowired
    private OrdersReposiotry ordersRepo;

    @GetMapping("/invoice/{orderId}")
    public String showInvoice(@PathVariable("orderId") Long orderId, Model model) {
        Orders order = ordersRepo.findById(orderId).orElse(null); // Lấy đối tượng Orders từ ID

        if (order != null) {
            model.addAttribute("order", order);
            return "user/invoice"; // Trả về template hiển thị hóa đơn
        } else {
            // Xử lý nếu không tìm thấy đơn hàng
            return "error"; // Trả về trang lỗi hoặc thông báo không tìm thấy đơn hàng
        }
    }
}
