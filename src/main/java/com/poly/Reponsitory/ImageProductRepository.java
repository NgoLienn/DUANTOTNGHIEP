package com.poly.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;

@Repository
public interface ImageProductRepository extends JpaRepository<Image_product, Long> {

    List<Image_product> findByProductId(Products productId);

    @Query("Select u from Products u where u.Name like %?1%")
    List<Products> findByNameContainingIgnoreCasee(String query);

    @Query("SELECT ip FROM Image_product ip WHERE ip.productId.Name LIKE %:query%")
    List<Image_product> findByNameContainingIgnoreCase(@Param("query") String query);

    List<Image_product> findAllByOrderByImageproductIdDesc();

}