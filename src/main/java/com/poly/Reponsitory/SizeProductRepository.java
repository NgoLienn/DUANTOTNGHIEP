package com.poly.Reponsitory;

import com.poly.Entity.Size_Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SizeProductRepository extends JpaRepository<Size_Product, Long> {
    @Query("select u from Size_Product u where u.Size.sizeID=?1")
    Size_Product findBySizeProductId(long sizeId);

}
