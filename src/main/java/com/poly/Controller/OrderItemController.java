package com.poly.Controller;

import java.util.List;
import java.util.UUID;

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
public class OrderItemController {

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

    @GetMapping("/orderitem/{id}")
    public String HistoryOrder(Model model, @PathVariable(value = "id") Long id, HttpServletRequest httpServlet) {

        Account account = accountRepo.findByUsername(httpServlet.getRemoteUser());

        List<Order_Items> orderItems = orderItemRepo.finByOrderItem(id);
        model.addAttribute("orderItems", orderItems);

        // Lấy trạng thái từ sản phẩm đầu tiên trong đơn hàng (nếu có)
        String orderStatus = "";
        if (!orderItems.isEmpty()) {
            orderStatus = orderItems.get(0).getOrders().getStatus().getName();
        }
        // Truyền trạng thái đơn hàng vào model
        model.addAttribute("orderStatus", orderStatus);

        return "user/orderitem";
    }

    @PostMapping("/rating/{id}")
    public String OrderAddRating(Model model,
            @PathVariable("id") Long orderitemid,
            Reviews review,
            @RequestParam(value = "saoo", required = false) Integer rating,
            @RequestParam(value = "description", required = false) String description,
            HttpServletRequest httpServlet) {

        Account account = accountRepo.findByUsername(httpServlet.getRemoteUser());

        Order_Items orderItems = orderItemRepo.getById(orderitemid);
        orderItems.setEvaluate(true);
        orderItemRepo.save(orderItems);

        Products products = new Products();
        products.setProductId(orderItems.getProduct().getProductId());
        review.setAccount(account);
        review.setProducts(products);
        review.setRating(rating);
        review.setComment(description);
        reviewRepo.save(review);

        return "redirect:/user/orderitem/" + orderItems.getOrders().getOrderID();
    }
}