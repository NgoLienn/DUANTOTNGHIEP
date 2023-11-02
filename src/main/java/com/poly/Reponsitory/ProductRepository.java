package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Products;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    // List<Products> findByCategoryId(Categories categoryId);

    // List<Image_product> getImageProductListByProductId(Long productId);\
    @Query("Select u from Products u where u.productId=?1")
    Products findByProduct(int productId);



}
