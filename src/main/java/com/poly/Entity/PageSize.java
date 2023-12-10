package com.poly.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSize {
    private List<Size_Product> ListSize;

    private int totalPages;
    private int currentPage;

    public List<Size_Product> getSizeList() {
        return ListSize;
    }

    public void setListSize(List<Size_Product> ListSize) {
        this.ListSize = ListSize;
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
