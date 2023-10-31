package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
