package com.poly.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategories(Categories categoryId);

    List<Image_product> getImageProductListByProductId(Long productId);
}
