package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Categories;
import com.poly.Reponsitory.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Categories> getAllCategories() {
        return categoryRepo.findAll();
    }

    // load thể loại theo id
    public Categories getCategoryId(Long categoryId) {
        return categoryRepo.findById(categoryId).get();
    }
    // end

    // delete category
    public void delete(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
    // end
}
