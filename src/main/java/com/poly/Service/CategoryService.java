package com.poly.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Categories;
import com.poly.Reponsitory.CategoryRepository;

@Service
public class CategoryService {
	 @Autowired
	 private CategoryRepository categoryRepo;
	 
	 @Autowired	    
	 private ProductService productService;
	 
	    public List<Categories> getAllCategories() {
	        return categoryRepo.findAll();
	    }	  
		public int totalCategory() {
			return categoryRepo.totalCategory();
		} 	   
	    
	    // load thể loại theo id
	    public Categories getCategoryId(Long categoryId) {
	        return categoryRepo.findById(categoryId).get();
	    }
	    public Categories save(Categories category) {
	        return categoryRepo.save(category);
	    }
	    // delete category
	    public void delete(Long categoryId) {
	        categoryRepo.deleteById(categoryId);
	    }
	    public Categories findById(Long categoryId) {
	        Optional<Categories> categoryOptional = categoryRepo.findById(categoryId);
	        return categoryOptional.orElse(null);
	    }
	    public boolean isCategoryInUse(Long categoryId) {
	        // Kiểm tra xem có sản phẩm nào sử dụng danh mục không
	        return productService.existsByCategoryId(categoryId);
	    }
	    public boolean isCategoryExists(String Name) {	    
	        Optional<Categories> existingCategory = categoryRepo.findByNameIgnoreCase(Name);
	        return existingCategory.isPresent();
	    }
		
}
