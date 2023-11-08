package com.poly.Controller;

import java.util.List;

import com.poly.Entity.Size;
import com.poly.Entity.Size_Product;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Reponsitory.SizeProductRepository;
import com.poly.Reponsitory.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/productDetail")
    public String getProducts(Model model,
            @RequestParam(value = "sizeID", defaultValue = "") Long sizeId,
            @RequestParam(value = "productID", defaultValue = "") int productId) {

        // lấy id sản phẩm
        Products produc = productService.getProductById(productId);
        model.addAttribute("product", produc);

        List<Size> size = sizeRepo.findBySizeID(productId);

        if (sizeId == null) {
            Long firstSizeId = null; // Initialize to null

            // Iterate through the list of sizeProducts to find the first size_id
            for (Size sizes : size) {
                firstSizeId = sizes.getSizeID();
                System.out.println(firstSizeId);
                break; // Exit the loop after finding the first size_id
            }
            Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(firstSizeId);
            model.addAttribute("sizeProduct", sizeProduct);

            // đếm lần xem sản phẩm
            productService.increaseViewCount(productId);
            Products product = productService.getProductById(productId);
            productService.Save(product);
        } else {
            Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(sizeId);
            model.addAttribute("sizeProduct", sizeProduct);
        }

        // lấy ảnh lên trong sản phẩm chi tiết
        List<Image_product> images = imageProductService.getImagesByProductId(productId);
        model.addAttribute("images", images);

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

        return "user/product_detail";
    }
}
