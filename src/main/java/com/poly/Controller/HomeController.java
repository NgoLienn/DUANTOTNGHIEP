package com.poly.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

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
    public String ViewProduct(Model model, HttpServletRequest httpServletRequest) {
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

                // Gán trung bình số sao vào model để hiển thị trên giao diện
                model.addAttribute("star" + productId, averageRating); // Đặt tên thuộc tính tương ứng với sản phẩm
            } else {
                // Xử lý nếu không có đánh giá cho sản phẩm
                model.addAttribute("star" + productId, 0); // Gán giá trị mặc định hoặc xử lý tùy thuộc vào logic của
                                                           // ứng dụng
            }
        }

        // Truyền danh sách sản phẩm và đánh giá vào model
        model.addAttribute("ListProduct", listProduct);
        model.addAttribute("reviews", reviewRepo.findAll()); // Truyền tất cả đánh giá để hiển thị nếu cần

        // sản phẩm theo thể loại
        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        String username = httpServletRequest.getRemoteUser();
        Carts carts = cartRepo.findByCartUser(username);
        if (carts == null || carts.getCartID() == null) {
            // Nếu giỏ hàng hoặc cartID là null, chuyển hướng người dùng đến trang thông báo
            return "user/index";
        } else {
            Long subtotal = cartItemsRepo.getSum(carts.getCartID());
            model.addAttribute("carts", carts);
            model.addAttribute("subtotal", subtotal);
        }

        return "user/index";
    }

    @GetMapping("index/remove/{cartitemID}")
    public String remove(@PathVariable("cartitemID") Long Id) {
        Cart_Items cartItems = cartItemsRepo.findByCartitemID(Id);
        cartItemsRepo.delete(cartItems);
        return "redirect:/";
    }
}
