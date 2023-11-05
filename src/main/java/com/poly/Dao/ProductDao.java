package com.poly.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.Entity.Products;

public interface ProductDao extends JpaRepository<Products, Integer> {

}
