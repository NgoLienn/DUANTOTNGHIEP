package com.poly.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Categories;
import com.poly.Entity.MapperCategory;

@Repository
public class CategoryDao {
    // extends JpaRepository<Categories, Long>
    @Autowired
    public JdbcTemplate jdbcTemplate;

    public List<Categories> getDataCategorys() {
        List<Categories> list = new ArrayList<Categories>();
        String sql = "SELECT * FROM Categories";
        list = jdbcTemplate.query(sql, new MapperCategory());
        return list;
        
    }
}
