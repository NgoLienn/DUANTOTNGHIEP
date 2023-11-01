package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ProductRepository;

@Service
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

    public Products get(long id) {
        return productrepo.findById(id).get();
    }

    public void delete(long id) {
        productrepo.deleteById(id);
    }

    // public List<Categories> getDataCategorys() {
    //     return categoryDao.getDataCategorys();
    // }

    // load sản phẩm theo id
    public Products getProductById(Long iddetail) {
        return productrepo.findById(iddetail).get();
    }
    // end

    // đưa ảnh lên theo id sản phẩm
    public List<Image_product> getImagesByProductId(Long productId) {
        return imageProductService.getImagesByProductId(productId);
    }
    // end

    // sử lý viewProduct
    public void increaseViewCount(Long iddetail) {
        Products product = productrepo.findById(iddetail).orElse(null);

        if (product != null) {
            int views = product.getViews() != null ? product.getViews() : 0;
            product.setViews(views + 1);
            productrepo.save(product);
        }
    }
    // end

    // phân trang sản phẩm
    // public Page<Products> getAll(int pageNo) {
    // Pageable pageable = PageRequest.of(pageNo - 1, 8);
    // return this.productrepo.findAll(pageable);
    // }
    // end

}
