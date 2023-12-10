package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Products;
import com.poly.Entity.Size;
import com.poly.Entity.Size_Product;
import com.poly.Entity.TableSize;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Reponsitory.SizeProductRepository;
import com.poly.Reponsitory.SizeRepository;
import com.poly.Reponsitory.SizeTableRepository;

@Service
public class SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SizeProductRepository sizeProductRepository;

    @Autowired
    SizeTableRepository sizeTableRepository;

    public List<Size_Product> findAllSizes() {
        return sizeProductRepository.findAllSizes();
    }

    public List<Size_Product> findProductsName(String key) {
        return sizeProductRepository.findNameProductSizes(key);
    }

    public Object findSizeProduct(Long id) {
        return sizeRepository.findSizeProduct(id);
    }

    public Size addSize(Products product, TableSize tableSize) {
        Size size = new Size();
        size.setProducts(product);
        size.setTableSize(tableSize);

        return sizeRepository.saveAndFlush(size);
    }

    public Size_Product addSize_Product(Size size, float price) {
        Size_Product size_Product = new Size_Product();

        size_Product.setSize(size);
        size_Product.setPrice(price);

        return sizeProductRepository.saveAndFlush(size_Product);
    }

    public TableSize addTableSize(String sizeName) {
        TableSize tableSize = new TableSize();

        tableSize.setSizeName(sizeName);

        return sizeTableRepository.saveAndFlush(tableSize);
    }

    public TableSize findTableSize(String sizeName) {
        return sizeTableRepository.findByNameSize(sizeName);
    }

    public Size findTableSizeIdAndProductId(int productId, int id) {
        return sizeRepository.findByTableSizeIdAndProductId(productId, id);
    }

    public Size_Product findBySizeProduct(Long id) {
        return sizeProductRepository.findSizeProductOne(id);
    }

    public void updateSize_Product(Size_Product size_Product, float price) {
        size_Product.setPrice(price);
        sizeProductRepository.save(size_Product);
    }

    public void deleteSize(Size size) {
        sizeRepository.delete(size);
    }

    public void deleteSizeProduct(Size_Product size_Product) {
        sizeProductRepository.delete(size_Product);
    }

}
