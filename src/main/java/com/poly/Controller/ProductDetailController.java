package com.poly.Controller;

import java.util.List;

import com.poly.Entity.Size;
import com.poly.Entity.Size_Product;
import com.poly.Reponsitory.SizeProductRepository;
import com.poly.Reponsitory.SizeRepository;
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

    @GetMapping("/productDetail/{iddetail}")
    public String getProducts(@PathVariable("iddetail") int iddetail, Model model, @RequestParam(value = "sizeId", defaultValue = "") Long sizeId) {

        // lấy id sản phẩm
        Products produc = productService.getProductById(iddetail);
        model.addAttribute("product", produc);

        List<Size> size = sizeRepo.findBySizeID(iddetail);


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
        }else {
            Size_Product sizeProduct = sizeProductRepo.findBySizeProductId(sizeId);
            model.addAttribute("sizeProduct", sizeProduct);
        }




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
