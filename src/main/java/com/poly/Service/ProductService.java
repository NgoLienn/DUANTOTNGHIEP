package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Dao.CategoryDao;
import com.poly.Entity.Categories;
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productrepo;

    // @Autowired
    // private final ProductRepository productRepository;

    @Autowired
    private CategoryDao categoryDao;

//    @Autowired
//    private ImageProductService imageProductService;

     public ProductService(ProductRepository productRepository) {
         this.productrepo = productRepository;
     }

    public List<Products> listAll() {
        return productrepo.findAll();
    }

    public void Save(Products product) {
        productrepo.save(product);
    }

    public Products get(long id) {
        return productrepo.findById(id).get();
    }

    public void delete(long id) {
        productrepo.deleteById(id);
    }

    // public List<Products> getProductByCategoryId(Categories category) {
    //     return productRepository.findByCategories(category);
    // }

    public List<Categories> getDataCategorys() {
        return categoryDao.getDataCategorys();
    }

    public Products getProductById(Long iddetail) {
        return productrepo.findById(iddetail).get();
    }

//    public List<Image_product> getImagesByProductId(Long productId) {
//        return imageProductService.getImagesByProductId(productId);
//    }

}
