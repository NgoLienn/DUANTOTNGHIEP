package com.poly.Controller.admin;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.Entity.Categories;
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.CategoryRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;

@Controller
@RequestMapping("/admin")
public class ManagerCategory {

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	Cloudinary cloudinary;

	@GetMapping("/managerCategory")
	public String ViewCategory(Model model) {

		// List<Categories> categories = categoryRepo.findAll();
		 
		 List<Categories> categories = categoryRepo.findAllByOrderByCategoryIdDesc();

	        // Thêm danh sách vào model để truyền đến view
	        model.addAttribute("categories", categories);

		 
		model.addAttribute("newCategories", new Categories());
		model.addAttribute("categories", categories);

		return "admin/category";
	}

	@PostMapping("/managerCategory/add")
	public String addProduct(@ModelAttribute("newCategories") Categories category, BindingResult bindingResult,
	        @RequestParam("uploadimage") MultipartFile file, Model model) {

	    Map<String, Object> params = ObjectUtils.asMap("folder", "Images_FastFoodStore", "resource_type", "image");
	    
	    if (categoryService.isCategoryExists(category.getName())) {
	        bindingResult.rejectValue("name", "error.newCategories", "DANH MỤC ĐÃ TỒN TẠI");
	        model.addAttribute("error", "DANH MỤC ĐÃ TỒN TẠI");
	        model.addAttribute("categories", categoryService.getAllCategories());
	        return "admin/category";
	    }
	    try {
	        // Bắt thêm ảnh
	        if (file == null || file.isEmpty()) {
	            model.addAttribute("error_image", "Vui lòng chọn ảnh.");
	            model.addAttribute("categories", categoryService.getAllCategories());
	            return "admin/category";
	        }

	        // Lưu trữ ảnh trực tiếp trong phương thức addProduct
	        String baseDir = System.getProperty("user.dir");
	        String folderPath = baseDir + File.separator + "src" + File.separator + "main" + File.separator
	                + "resources" + File.separator + "static" + File.separator + "assets" + File.separator + "images"
	                + File.separator + "img_product";

	        File directory = new File(folderPath);

	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
	        File savedFile = new File(folderPath, fileName);
	        file.transferTo(savedFile);

	      
	        

	        // Cloudinary upload sử dụng ảnh đã được chỉnh sửa
	        Map<String, Object> uploadResult = cloudinary.uploader().upload(savedFile, params);
	        String url = uploadResult.get("url").toString();

	        category.setImage_url(url);
	      
	        
	        categoryRepo.save(category);

	        return "redirect:/admin/managerCategory";

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "redirect:/admin/managerCategory";
	}


	@GetMapping("/managerCategory/edit/{categoryId}")
	public String edit(Model model, @PathVariable("categoryId") Long categoryId) {

		model.addAttribute("categoryIdd", categoryId);

		List<Categories> categories = categoryRepo.findAllByOrderByCategoryIdDesc();
		model.addAttribute("categories", categories);

		Categories editCategories = categoryService.getCategoryId(categoryId);
		model.addAttribute("newCategories", editCategories);

		return "admin/category";
	}

	@PostMapping("/managerCategory/update/{id}")
	public String updateCategory(Model model, @ModelAttribute @Valid Categories category, HttpServletRequest req,
			BindingResult bindingResult, @PathVariable("id") Long id, @RequestParam("filess") String filess,
			@RequestParam("uploadimage") MultipartFile file) {

		Categories existingCaterory = categoryRepo.findById(category.getCategoryId()).orElse(null);

		Map params = ObjectUtils.asMap("folder", "Images_FastFoodStore", "resource_type", "image");

		if (existingCaterory != null) {
			existingCaterory.setName(category.getName());
			Boolean newStatus = category.getStatus();
			if (newStatus != null && !newStatus.equals(existingCaterory.getStatus())) {
				existingCaterory.setStatus(newStatus); // Cập nhật trạng thái mới cho sản phẩm
			}
			System.out.println(filess);
			String temp = null;
			if (file != null && !file.isEmpty()) {
				try {
					Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
					String url = uploadResult.get("url").toString();

					temp = url;
				} catch (Exception e) {
				}
			} else {
				temp = filess;
			}
			existingCaterory.setImage_url(temp);
			categoryRepo.save(existingCaterory);
		}

		return "redirect:/admin/managerCategory";
	}

}
