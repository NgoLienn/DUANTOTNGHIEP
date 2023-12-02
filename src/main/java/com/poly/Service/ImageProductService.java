package com.poly.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ImageProductRepository;
import com.poly.Reponsitory.ProductRepository;

@Service
public class ImageProductService {

    @Autowired
    private ImageProductRepository imageProductRepo;

    @Autowired
    private ProductRepository productrepo;

    public List<Image_product> getImagesByProductId(int productId) {
        // return imageProductRepository.findByProductId(productId);
        Products product = productrepo.findById(productId).orElse(null);
        if (product != null) {
            return imageProductRepo.findByProductId(product);
        }
        return Collections.emptyList();
    }

    // load sản phẩm theo id
    public Image_product getImageProductById(Long imageproductId) {
        return imageProductRepo.findById(imageproductId).get();
    }
    // end

    public void delete(Long iamgeproduct) {
        imageProductRepo.deleteById(iamgeproduct);
    }

    // // tìm kiếm
    public List<Image_product> searchProducts(String query) {
        return imageProductRepo.findByNameContainingIgnoreCase(query);
    }
    // // end
}
