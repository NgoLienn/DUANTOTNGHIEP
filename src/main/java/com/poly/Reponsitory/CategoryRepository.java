package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
    // @Query("SELECT c FROM Categories c WHERE c.id = :categoryId")
    // Optional<Categories> getCategoryById(Categories categoryId);
    @Query(value = "select count(category_id) from Categories", nativeQuery = true)
    public int totalCategory();
}
