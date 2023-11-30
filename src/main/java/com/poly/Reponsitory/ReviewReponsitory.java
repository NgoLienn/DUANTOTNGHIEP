package com.poly.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Reviews;

@Repository
public interface ReviewReponsitory extends JpaRepository<Reviews, Integer> {

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1 and u.Rating=1")
    List<Reviews> finByProductAndRatingOne(int productId);

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1 and u.Rating=2")
    List<Reviews> finByProductAndRatingTwo(int productId);

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1 and u.Rating=3")
    List<Reviews> finByProductAndRatingThree(int productId);

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1 and u.Rating=4")
    List<Reviews> finByProductAndRatingFour(int productId);

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1 and u.Rating=5")
    List<Reviews> finByProductAndRatingFive(int productId);

    @Query("SELECT u FROM Reviews u WHERE u.Products.productId=?1")
    List<Reviews> finByProductRating(int productId);

}