package com.poly.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageAccount {
    private List<Authority> ListAuthority;

    private int totalPages;
    private int currentPage;

    public List<Authority> getAuthorityList() {
        return ListAuthority;
    }

    public void setListAuthority(List<Authority> ListAuthority) {
        this.ListAuthority = ListAuthority;
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
