package com.poly.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Account;
import com.poly.Entity.Carts;
import com.poly.Entity.Orders;
import com.poly.Entity.Size_Product;
import com.poly.Entity.Status;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
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

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/order")
    public String HistoryOrder(Model model, Authentication authentication) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Account account = accountRepo.findByUsername(users);
        List<Orders> orders = ordersRepo.finByUsernam(account.getUserName());

        // Sắp xếp danh sách đơn hàng theo thời gian đặt hàng từ mới nhất đến cũ nhất
        orders.sort((order1, order2) -> order2.getOrderTime().compareTo(order1.getOrderTime()));

        model.addAttribute("orders", orders);

        List<Size_Product> sizeProducts = sizeProductRepo.findAll();
        model.addAttribute("sizeProducts", sizeProducts);

        // cart small
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }
        Carts carts = cartRepo.findByCartUser(users);
        if (carts == null) {
            return "redirect:/user/CartNull";
        } else {
            Long subtotal = cartItemsRepo.getSum(carts.getCartID());
            model.addAttribute("subtotal", subtotal);
            model.addAttribute("carts", carts);
        }
        // end

        return "user/order";
    }

    // hủy đơn hàng
    @PostMapping("/orderCancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đã hủy đơn" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(6L).orElse(null); // Assuming the ID for "Đã hủy đơn" is 6
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

    // đã nhận hàng
    @PostMapping("/orderReceived/{orderId}")
    public String receivedOrder(@PathVariable Long orderId, Model model) {
        // Tìm đơn hàng dựa trên orderId từ cơ sở dữ liệu
        Orders order = ordersRepo.findById(orderId).orElse(null);
        if (order != null) {
            // Lấy trạng thái "Đã nhận hàng" từ cơ sở dữ liệu
            Status cancelledStatus = statusRepo.findById(5L).orElse(null); // Assuming the ID for "Đã nhận hàng" is 6
            // Kiểm tra xem trạng thái đã tìm được hay không
            if (cancelledStatus != null) {
                // Cập nhật trạng thái đơn hàng thành "Đã nhận hàng"
                order.setStatus(cancelledStatus);
                // Lưu thay đổi vào cơ sở dữ liệu
                ordersRepo.save(order);
            }
        }
        return "redirect:/user/order";
    }

}