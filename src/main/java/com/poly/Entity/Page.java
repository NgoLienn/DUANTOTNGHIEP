package com.poly.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page {
    private List<Products> ListProduct;

    private int totalPages;
    private int currentPage;

    public List<Products> getProductsList() {
        return ListProduct;
    }

    public void setProductsList(List<Products> ListProduct) {
        this.ListProduct = ListProduct;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}