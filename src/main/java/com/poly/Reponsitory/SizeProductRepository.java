package com.poly.Reponsitory;

import com.poly.Entity.Size_Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SizeProductRepository extends JpaRepository<Size_Product, Long> {
    @Query("select u from Size_Product u where u.Size.sizeID=?1")
    Size_Product findBySizeProductId(long sizeId);

    @Query("select u from Size_Product u where u.Size.sizeID =:id")
    Size_Product findSizeProductOne(Long id);

    @Query("Select u from Size_Product u")
    List<Size_Product> findAllSizes();

    @Query("Select u from Size_Product u where u.Size.Products.Name like %?1%")
    List<Size_Product> findNameProductSizes(String key);
}
