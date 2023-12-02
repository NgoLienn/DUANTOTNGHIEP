package com.poly.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Reponsitory.ReviewReponsitory;




@Service
public class ReviewService {
	private final ReviewReponsitory reviewRepo;
	
	@Autowired
	public ReviewService(ReviewReponsitory reviewRepo) {
		this.reviewRepo = reviewRepo;
	}
	public List<Reviews> getAllReview() {
		return reviewRepo.findAll();
	}
	public Reviews getReviewById(Integer reviewID) {
		return reviewRepo.findById(reviewID).get();
	}
	
	public List<Reviews> searchReviews(String query) {
        return reviewRepo.searchReview(query);
    }
	public void delete(Integer reviewID) {
		reviewRepo.deleteById(reviewID);
    }

}
