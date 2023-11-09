package com.poly.Reponsitory;

import com.poly.Entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    @Query("Select u from Size u where u.Products.productId=?1")
    List<Size> findBySizeID(int productId);

    @Query("select  u from Size u where u.sizeID=?1")
    Size findBySizeName(long sizeId );

    @Query("select u from Size u where u.Products.productId=?1 and u.tableSize.sizeName=?2")
            Size findByProductAndSizeName(int product, String sizeName);




}