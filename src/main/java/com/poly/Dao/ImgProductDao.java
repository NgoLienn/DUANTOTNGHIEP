package com.poly.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Image_product;

@Repository
public interface ImgProductDao extends JpaRepository<Image_product, Long> {

}
