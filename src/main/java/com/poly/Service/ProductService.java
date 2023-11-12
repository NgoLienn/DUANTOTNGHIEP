package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;

@Service
// @Async
public class ProductService {

    @Autowired
    private ProductRepository productrepo;

    @Autowired
    private ImageProductService imageProductService;

    public List<Products> listAll() {
        return productrepo.findAll();
    }

    public void Save(Products product) {
        productrepo.save(product);
    }

    public Products get(int id) {
        return productrepo.findById(id).get();
    }

    public void delete(int id) {
        productrepo.deleteById(id);
    }

    public Products create(Products id) {
        return productrepo.save(id);
    }

    public Products update(Products product) {
        return productrepo.save(product);
    }

    // public List<Categories> getDataCategorys() {
    // return categoryDao.getDataCategorys();
    // }

    // load sản phẩm theo id
    public Products getProductById(int iddetail) {
        return productrepo.findById(iddetail).get();
    }
    // end

    // đưa ảnh lên theo id sản phẩm
    public List<Image_product> getImagesByProductId(int productId) {
        return imageProductService.getImagesByProductId(productId);
    }
    // end

    // sử lý viewProduct
    public void increaseViewCount(int iddetail) {
        Products product = productrepo.findById(iddetail).orElse(null);

        if (product != null) {
            int views = product.getViews() != null ? product.getViews() : 0;
            product.setViews(views + 1);
            productrepo.save(product);
        }
    }

    // end

    // // tìm kiếm
    public List<Products> searchProducts(String query) {
        return productrepo.findByNameContainingIgnoreCase(query);
    }
    // // end

}
