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

    @Query("Select u from Products u where u.Name like %?1%" +
            "OR u.Price LIKE %?1% ")
    List<Products> findByNameContainingIgnoreCase(String query);

    @Query("SELECT u.categoryId.Name, COUNT(u.categoryId) AS SoLuong FROM Products u GROUP BY u.categoryId.Name")
    List<Object> countCategoryName();

    @Query("Select count(u.productId) from Products u")
    public int countProduct();

    @Query("Select p from Products p where p.Name like %?1%")
    public Products findByProductName(String name);

    @Query("Select u from Products u where u.Name = ?1")
    Products findByProductNamee(String name);
    
    boolean existsByCategoryId(Long categoryId);
   
}
