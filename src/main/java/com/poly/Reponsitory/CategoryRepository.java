package com.poly.Reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;
import com.poly.Entity.Products;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    @Query(value = "select count(category_id) from Categories", nativeQuery = true)
    public int totalCategory();
    
    @Query("Select u from Categories u where u.Name = ?1")
    Categories findByCategoryName(String name);
    
 
   

	

	
}
