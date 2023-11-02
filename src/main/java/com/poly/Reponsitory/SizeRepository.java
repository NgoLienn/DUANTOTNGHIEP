package com.poly.Reponsitory;

import com.poly.Entity.Products;
import com.poly.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    @Query("Select u from Size u where u.Products.productId=?1")
    List<Size> findBySizeID(int productId);



}