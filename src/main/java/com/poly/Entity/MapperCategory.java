package com.poly.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MapperCategory implements RowMapper<Categories> {
    public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
        Categories categories = new Categories();
        categories.setCategoryId(rs.getLong("category_id"));
        categories.setName(rs.getString("name"));
        categories.setDescription(rs.getString("description"));
        categories.setImage_url(rs.getString("image_url"));
        return categories;
    }
}
