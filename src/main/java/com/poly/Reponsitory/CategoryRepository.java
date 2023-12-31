package com.poly.Reponsitory;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;


@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    @Query(value = "select count(category_id) from Categories", nativeQuery = true)
    public int totalCategory();
    
    @Query("Select u from Categories u where u.Name = ?1")
    Categories findByCategoryName(String name);
    
    @Query("SELECT c FROM Categories c WHERE LOWER(c.Name) = LOWER(:name)")
    Optional<Categories> findByNameIgnoreCase(@Param("name") String name);
 
    
    List<Categories> findAllByOrderByCategoryIdDesc();

	
	

	
}
