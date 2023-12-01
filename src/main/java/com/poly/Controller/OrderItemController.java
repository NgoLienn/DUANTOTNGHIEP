package com.poly.Controller;

import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Entity.Account;
import com.poly.Entity.Carts;
import com.poly.Entity.Order_Items;
import com.poly.Entity.Orders;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Entity.Size_Product;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
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

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/orderitem/{id}")
    public String HistoryOrder(Model model, @PathVariable(value = "id") Long id, HttpServletRequest httpServlet,
            Authentication authentication) {

        Account account = accountRepo.findByUsername(httpServlet.getRemoteUser());

        List<Order_Items> orderItems = orderItemRepo.finByOrderItem(id);
        model.addAttribute("orderItems", orderItems);

        String users = "";
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