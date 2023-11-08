package com.poly.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.Entity.Products;
import com.poly.Service.ProductService;

@RestController
@RequestMapping("/admin/products")
public class ManagerProductsRestController {
    @Autowired
    ProductService productService;

    @GetMapping
    public List<Products> getAll() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public Products getone(@PathVariable("id") Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Products create(@RequestBody Products product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Products update(@PathVariable("id") Integer id, @RequestBody Products product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }
}
