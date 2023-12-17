package com.poly.Controller.admin;

import java.util.List;

import com.poly.Entity.*;
import com.poly.Reponsitory.AuthorityResponsitory;
import com.poly.Reponsitory.RoleRespontory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.Reponsitory.ReviewReponsitory;
import com.poly.Service.ReviewService;
import com.cloudinary.Cloudinary;


@Controller
@RequestMapping("/admin")
public class ManagerAuthorization {
	@Autowired
	RoleRespontory roleRespontory;
	@Autowired
	AuthorityResponsitory authorityResponsitory;
	private final int pageSize = 8;
	@GetMapping("/managerAuthorization")
	public String index(Model m,@RequestParam(defaultValue = "1") int page,
						@RequestParam(defaultValue = "") String keyword){
		List<Roles> role =roleRespontory.findAll();
		m.addAttribute("roles",role);
		List<Authority>authorities;

		if (keyword.equals("")) {
			//
			authorities =authorityResponsitory.findByRoleAdmin();
		} else {
			System.out.println(keyword);
			authorities = authorityResponsitory.findBysearch(keyword);
		}
		m.addAttribute("authorities",authorities);
		int totalAccounts = authorities.size();
		int totalPages = (int) Math.ceil(totalAccounts / (double) pageSize);

		// Lấy danh sách tài khoản trên trang hiện tại
		int start = (page - 1) * pageSize;
		int end = Math.min(start + pageSize, totalAccounts);
		List<Authority> accountsOnPage = authorities.subList(start, end);

		// Đưa thông tin về dữ liệu và phân trang vào Model
		Page accountPage = new Page();
		accountPage.setListAuthority(authorities);
		accountPage.setTotalPages(totalPages);
		accountPage.setCurrentPage(page);
		m.addAttribute("accountPage", accountPage);
		return "admin/authorization";

	}
	@PostMapping("/set-role")
	@ResponseBody
	public String setRole(@RequestParam("username") String username, @RequestParam("role") String role) {
//        System.out.println(username);
//        System.out.println(role);
		Authority authority = authorityResponsitory.findByUserName(username);
		Roles newRole = roleRespontory.getById(role);
		authority.setRole(newRole);
		authorityResponsitory.save(authority);
		return "Success";
	}
	
}
