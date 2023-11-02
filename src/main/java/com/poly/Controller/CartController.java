package com.poly.Controller;

import java.util.List;
import java.util.Optional;

import com.poly.Reponsitory.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Dao.ProductDao;
import com.poly.Entity.Account;
import com.poly.Entity.Cart_Items;
import com.poly.Entity.Carts;
import com.poly.Entity.Products;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CartItemsService;
import com.poly.Service.CartService;
import com.poly.Service.ProductService;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;

@Controller

public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    CartRepository cartRepon;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountReponsitory accountRepo;

    @GetMapping("/product")
    public String listProducts(Model model) {
        List<Products> products = productService.listAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        // Lấy thông tin giỏ hàng và hiển thị nó
        return "user/cart";
    }

    @PostMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable int productId, Model model, HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getRemoteUser();
        Account account = accountRepo.findByUsername(username);
        Carts cart = cartRepo.findByCartsUsername(account.getAccountID());
        if (cart == null) {
            Carts newCart = new Carts();
            newCart.setAccount(account);
            cartRepon.save(newCart);
            // Lấy sản phẩm từ cơ sở dữ liệu bằng productId
            Products product = productRepo.findByProduct(productId);

            Cart_Items cartItems = new Cart_Items();

            cartItems.setProductId(product);
            cartItems.setCarts(newCart);
            cartItems.setQuantity(1);
            cartItems.setPrice(product.getPrice());
            cartItems.setSubtotal(1 * product.getPrice());
            cartItemsRepo.save(cartItems);
            return "redirect:/cart";
        }

        // // Lấy giỏ hàng của người dùng (có thể sử dụng session để xác định người
        // dùng)
        // Carts userCart = getUserCart();

        // // Thêm sản phẩm vào giỏ hàng
        // userCart.getProducts().add(product);

        // // Lưu thông tin giỏ hàng vào cơ sở dữ liệu
        // cartRepo.save(userCart);

        return "redirect:/cart"; // Chuyển hướng đến trang giỏ hàng
    }


}
