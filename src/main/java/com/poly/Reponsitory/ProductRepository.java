package com.poly.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Products;


@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query("Select u from Products u where u.productId=?1")
    Products findByProduct(int productId);

    @Query("Select u from Products u where u.Name like %?1%")
    List<Products> findByNameContainingIgnoreCase(String query);
}
