package com.poly.Controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Entity.Page;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Service.ReviewService;
import com.cloudinary.Cloudinary;


@Controller
@RequestMapping("/admin")
public class ManagerReview {
	@Autowired
	ReviewReponsitory reviewRepo;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	Cloudinary cloudinary;

	private final int pageSize = 2;

	@GetMapping("/managerReview")
	public String ViewCategory(Model model, @RequestParam(defaultValue = "1") int page,
			@RequestParam(value = "query", defaultValue = "") String query) {
		List<Reviews> listReview;
		List<Reviews> reviewList = reviewService.getAllReview();
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("newReview", new Reviews());
		model.addAttribute("true", true);
				
		  if (query.equals("")) {
			  listReview = reviewRepo.findAll();
	        } else {
	        	listReview = reviewService.searchReviews(query);

	        }
		  model.addAttribute("query", query);
		     model.addAttribute("ListReview", listReview);
		//Phân trang
		
		int totalReview = listReview.size();
        int totalPages = (int) Math.ceil(totalReview / (double) pageSize);     
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalReview);
        List<Reviews> reviewsOnPage = listReview.subList(start, end);
        // Đưa thông tin về dữ liệu và phân trang vào Model
        Page reviewPage = new Page();
        reviewPage.setReviewList(reviewsOnPage);
        reviewPage.setTotalPages(totalPages);
        reviewPage.setCurrentPage(page);
        model.addAttribute("reviewPage", reviewPage);

		return "/admin/review";
	}
	
	@PostMapping("/managerReview/reset")
	public String resetForm(@ModelAttribute("newReview") Reviews newReview, Model model) {
		// Tạo một đối tượng mới để làm trắng form
		Reviews emptyReview = new Reviews();
		model.addAttribute("newCategory", emptyReview);
		List<Reviews> categoryList = reviewService.getAllReview();
		model.addAttribute("categoryList", categoryList);
	
		return "redirect:/admin/managerReview";
	}

	@GetMapping("/managerReview/edit/{reviewID}")
	public String edit(Model model, @PathVariable("reviewID") Integer reviewID,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(value = "query", defaultValue = "") String query) {

		model.addAttribute("reviewID", reviewID);

		List<Reviews> listReview;

		Reviews editReview = reviewService.getReviewById(reviewID);

		model.addAttribute("false", false);

		model.addAttribute("newReview", editReview);

		List<Reviews> reviewList = reviewService.getAllReview();
		model.addAttribute("reviewList", reviewList);
		 if (query.equals("")) {
			  listReview = reviewRepo.findAll();
	        } else {
	        	listReview = reviewService.searchReviews(query);

	        }
		 model.addAttribute("query", query);
	     model.addAttribute("ListReview", listReview);
		//Phân trang
		
		int totalReview = listReview.size();
       int totalPages = (int) Math.ceil(totalReview / (double) pageSize);     
       int start = (page - 1) * pageSize;
       int end = Math.min(start + pageSize, totalReview);
       List<Reviews> reviewsOnPage = listReview.subList(start, end);
       // Đưa thông tin về dữ liệu và phân trang vào Model
       Page reviewPage = new Page();
       reviewPage.setReviewList(reviewsOnPage);
       reviewPage.setTotalPages(totalPages);
       reviewPage.setCurrentPage(page);
       model.addAttribute("reviewPage", reviewPage);
		return "/admin/review";
	}

	@PostMapping("/managerReview/delete/{reviewID}")
	public String deleteReview(Model model, @PathVariable("reviewID") Integer reviewID) {
		try {
			reviewService.delete(reviewID);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/managerReview";
	}

}
