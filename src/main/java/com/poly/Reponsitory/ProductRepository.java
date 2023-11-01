package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    // List<Products> findByCategoryId(Categories categoryId);

    // List<Image_product> getImageProductListByProductId(Long productId);
}
