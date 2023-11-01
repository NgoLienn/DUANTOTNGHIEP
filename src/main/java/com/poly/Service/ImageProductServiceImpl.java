// package com.poly.Service;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.poly.Entity.Image_product;
// import com.poly.Reponsitory.ImageProductRepository;

// @Service
// public class ImageProductServiceImpl implements ImageProductService {
//     @Autowired
//     private ImageProductRepository imageProductRepository;

//     @Override
//     public List<Image_product> getImagesByProductId(Long productId) {
//         return imageProductRepository.findByProductId(productId);
//     }
// }