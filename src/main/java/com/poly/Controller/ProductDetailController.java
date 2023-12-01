package com.poly.Controller;

import java.util.List;

import com.poly.Entity.Size;
import com.poly.Entity.Size_Product;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Reponsitory.SizeProductRepository;
import com.poly.Reponsitory.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Carts;
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Service.ImageProductService;
import com.poly.Service.ProductService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageProductService imageProductService;

    @Autowired
    private SizeRepository sizeRepo;

    @Autowired
    private SizeProductRepository sizeProductRepo;

    @Autowired
    ReviewReponsitory reviewRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping("/productDetail")
    public String getProducts(Model model,
            @RequestParam(value = "sizeID", defaultValue = "") Long sizeId,
            @RequestParam(value = "productID", defaultValue = "") int productId, Authentication authentication) {

        // lấy id sản phẩm
        Products produc = productService.getProductById(productId);
        model.addAttribute("product", produc);
        // lấy idcategory
        model.addAttribute("productCategory", produc.getCategoryId());

        List<Size> size = sizeRepo.findBySizeID(productId);
        Long firstName = null;
        if (sizeId == null) {
            Long firstSizeId = null; // Initialize to null
            // Iterate through the list of sizeProducts to find the first size_id
            for (Size sizes : size) {
                firstSizeId = sizes.getSizeID();
                break; // Exit the loop after finding the first size_id
            }
            Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(firstSizeId);
            model.addAttribute("sizeProduct", sizeProduct);

            firstName = firstSizeId;
            // đếm lần xem sản phẩm
            productService.increaseViewCount(productId);
            Products product = productService.getProductById(productId);
            productService.Save(product);
        } else {
            Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(sizeId);
            model.addAttribute("sizeProduct", sizeProduct);
            firstName = sizeId;
        }

        Size sizeName = sizeRepo.findBySizeName(firstName);
        String sizeNames = sizeName.getTableSize().getSizeName();
        model.addAttribute("sizeNames", sizeNames);
        // model.addAttribute("sizeNames",sizename);

        // lấy ảnh lên trong sản phẩm chi tiết
        List<Image_product> images = imageProductService.getImagesByProductId(productId);
        model.addAttribute("images", images);

        // tính trung bình số sao
        List<Reviews> reviews = reviewRepo.finByProductRating(productId);
        float star = 0;
        int i = 0;

        // Kiểm tra xem có đánh giá nào không trước khi tính toán trung bình số sao
        if (!reviews.isEmpty()) {
            for (Reviews rv : reviews) {
                star += rv.getRating();
                i += 1;
            }
            float averageRating = star / i;
            System.out.println(averageRating);
            model.addAttribute("star", averageRating);
        } else {
            // Nếu không có đánh giá nào, gán giá trị mặc định hoặc xử lý theo ý của bạn
            model.addAttribute("star", 0); // hoặc có thể gán giá trị mặc định khác tùy thuộc vào logic của ứng dụng
        }

        model.addAttribute("reviews", reviews);

        // đánh giá
        List<Reviews> review1 = reviewRepo.finByProductAndRatingOne(productId);
        model.addAttribute("review1", review1);

        List<Reviews> review2 = reviewRepo.finByProductAndRatingTwo(productId);
        model.addAttribute("review2", review2);

        List<Reviews> review3 = reviewRepo.finByProductAndRatingThree(productId);
        model.addAttribute("review3", review3);

        List<Reviews> review4 = reviewRepo.finByProductAndRatingFour(productId);
        model.addAttribute("review4", review4);

        List<Reviews> review5 = reviewRepo.finByProductAndRatingFive(productId);
        model.addAttribute("review5", review5);
        // end

        // cart small
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
        // end

        return "user/product_detail";
    }
}
