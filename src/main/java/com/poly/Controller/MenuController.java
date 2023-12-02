package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
@RequestMapping("/")
public class MenuController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ReviewReponsitory reviewRepo;
    
    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/menu")
    public String ViewMenuProducts(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
            Authentication authentication) {
        // load toàn bộ sản phẩm
        List<Products> listProduct = productRepo.findAll();

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
        model.addAttribute("ListProduct", listProduct);

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        // phân trang sản phẩm
        // Page<Products> listpage = this.productService.getAll(pageNo);
        // model.addAttribute("ListProduct", listpage);
        // model.addAttribute("currentPage", pageNo);
        // model.addAttribute("totalPage", listpage.getTotalPages());

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
        //     return "redirect:/menu";
        // } else {
        //     Long subtotal = cartItemsRepo.getSum(carts.getCartID());
        //     model.addAttribute("subtotal", subtotal);
        //     model.addAttribute("carts", carts);
        // }

        return "user/menu";
    }
}
