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
public class ManagerAuthorization {

	@GetMapping("/managerAuthorization")
	public String ViewAuthorization(Model model) {

		return "/admin/authorization";
	}
	
}
