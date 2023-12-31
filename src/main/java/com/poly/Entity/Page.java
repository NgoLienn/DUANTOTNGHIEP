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
	private List<Authority> ListAuthority;

	public List<Authority> getListAuthority() {
		return ListAuthority;
	}

	public void setListAuthority(List<Authority> listAuthority) {
		ListAuthority = listAuthority;
	}

	private List<Image_product> imageProduct;

	private int totalPages;
	private int currentPage;
	private List<Reviews> listReview;

	public List<Reviews> getReviewsList() {
		return listReview;
	}

	public void setReviewList(List<Reviews> listReview) {
		this.listReview = listReview;
	}

	public List<Image_product> getProductsImageList() {
		return imageProduct;
	}

	public void setProductsImageList(List<Image_product> imageProduct) {
		this.imageProduct = imageProduct;
	}

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