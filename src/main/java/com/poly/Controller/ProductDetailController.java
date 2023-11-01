package com.poly.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Service.ImageProductService;
import com.poly.Service.ProductService;

@Controller
@RequestMapping("/user")
public class ProductDetailController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ImageProductService imageProductService;

    @GetMapping("/productDetail/{iddetail}")
    public String getProducts(@PathVariable("iddetail") Long iddetail, Model model) {
        // lấy id sản phẩm
        Products produc = productService.getProductById(iddetail);
        model.addAttribute("product", produc);

        // lấy ảnh lên trong sản phẩm chi tiết
        List<Image_product> images = imageProductService.getImagesByProductId(iddetail);
        model.addAttribute("images", images);

        // đếm lần xem sản phẩm
        productService.increaseViewCount(iddetail);
        Products product = productService.getProductById(iddetail);
        productService.Save(product);

        return "user/product_detail";
    }
}
