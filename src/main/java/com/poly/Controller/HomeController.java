package com.poly.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import com.poly.Entity.Cart_Items;
import com.poly.Entity.Carts;
import com.poly.Entity.Categories;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Service.CategoryService;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @Autowired
    ReviewReponsitory reviewRepo;

    @GetMapping(value = { "/", "/user/index" })
    public String ViewProduct(Model model, HttpServletRequest httpServletRequest, Authentication authentication) {
        // load toàn bộ sản phẩm
        List<Products> listProduct = productRepo.findAll(); // Lấy danh sách sản phẩm

        for (Products product : listProduct) {
            int productId = product.getId();
            List<Reviews> reviews = reviewRepo.finByProductRating(productId); // Lấy đánh giá cho sản phẩm cụ thể

            float star = 0;
            int reviewCount = reviews.size();

            if (reviewCount > 0) {
                for (Reviews rv : reviews) {
                    star += rv.getRating();
                }
                float averageRating = star / reviewCount;
                System.out.println("Product ID: " + productId + ", Average Rating: " + averageRating);

                // Đặt giá trị trung bình vào thuộc tính của sản phẩm
                product.setAverage_rating(averageRating);

                // Gán trung bình số sao vào model để hiển thị trên giao diện
                model.addAttribute("star", product); // Đặt tên thuộc tính tương ứng với sản phẩm

            } else {
                // Xử lý nếu không có đánh giá cho sản phẩm
                model.addAttribute("star", 0);
            }
        }

        // Truyền danh sách sản phẩm và đánh giá vào model
        model.addAttribute("ListProduct", listProduct);
        model.addAttribute("reviews", reviewRepo.findAll()); // Truyền tất cả đánh giá để hiển thị nếu cần

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        // String users = "";
        // if (authentication instanceof OAuth2AuthenticationToken) {
        //     OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        //     OAuth2User user = oauthToken.getPrincipal();
        //     users = user.getAttribute("email");
        // } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
        //     users = authentication.getName();
        // }
        // Carts carts = cartRepo.findByCartUser(users);
        // if (carts == null) {
        //     return "redirect:/user/CartNull";
        // } else {
        //     Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        //     model.addAttribute("subtotal", subtotal);
        //     model.addAttribute("carts", carts);
        // }
        return "user/index";
    }

    // @GetMapping("index/remove/{cartitemID}")
    // public String remove(@PathVariable("cartitemID") Long Id) {
    //     Cart_Items cartItems = cartItemsRepo.findByCartitemID(Id);
    //     cartItemsRepo.delete(cartItems);
    //     return "redirect:/";
    // }
}
