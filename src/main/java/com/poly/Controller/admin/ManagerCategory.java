package com.poly.Controller.admin;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

		List<Categories> categories = categoryRepo.findAll();

		model.addAttribute("newCategories", new Categories());
		model.addAttribute("categories", categories);

		return "admin/category";
	}

	@GetMapping("/managerCategory/edit/{categoryId}")
	public String edit(Model model, @PathVariable("categoryId") Long categoryId) {

		model.addAttribute("categoryIdd", categoryId);

		List<Categories> categories = categoryRepo.findAll();
		model.addAttribute("categories", categories);

		Categories editCategories = categoryService.getCategoryId(categoryId);
		model.addAttribute("newCategories", editCategories);

		return "admin/category";
	}

	@PostMapping("/managerCategory/update/{id}")
	public String updateCategory(Model model, @ModelAttribute @Valid Categories category,
			HttpServletRequest req, BindingResult bindingResult, @PathVariable("id") Long id,
			@RequestParam("filess") String filess, @RequestParam("uploadimage") MultipartFile file) {

		Categories existingCaterory = categoryRepo.findById(category.getCategoryId()).orElse(null);

        Map params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");

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

	@PostMapping("/managerCategory/add")
	public String addProduct(@ModelAttribute("newCategories") Categories categories, BindingResult bindingResult,
			@RequestParam("uploadimage") MultipartFile file, HttpServletRequest req, Model model) {

		 Map<String, Object> params = ObjectUtils.asMap(
	                "folder", "Images_FastFoodStore",
	                "resource_type", "image");

	        try {
	            Categories existingCategory = categoryRepo.findByCategoryName(categories.getName());
	            if (existingCategory != null) {
	                // Tên danh mục đã tồn tại trong cơ sở dữ liệu
	                bindingResult.rejectValue("name", "error.product", "Tên danh mục đã tồn tại");
	                model.addAttribute("nameError", "Tên danh mục đã tồn tại");

	            } else {
	                // Your existing code to save the original file
	                String baseDir = System.getProperty("user.dir");
	                String folderPath = baseDir + File.separator + "src" + File.separator + "main" + File.separator
	                        + "resources"
	                        + File.separator + "static" + File.separator + "assets" + File.separator + "images"
	                        + File.separator
	                        + "img_product";
	                File directory = new File(folderPath);

	                if (!directory.exists()) {
	                    directory.mkdirs();
	                }

	                String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
	                File savedFile = new File(directory, fileName);

	                file.transferTo(savedFile);

	                // API to remove background
	                Response response = Request.Post("https://api.remove.bg/v1.0/removebg")
	                        .addHeader("X-Api-Key", "GN2ULwMosewpuewn4vJe4GPg")
	                        .body(
	                                MultipartEntityBuilder.create()
	                                        .addBinaryBody("image_file", savedFile)
	                                        .addTextBody("size", "auto")
	                                        .build())
	                        .execute();

	                // Save the modified image
	                String modifiedFileName = System.currentTimeMillis() + "-no-bg-" + file.getOriginalFilename();
	                File modifiedImageFile = new File(directory, modifiedFileName);
	                response.saveContent(modifiedImageFile);

	                // Cloudinary upload using the modified image
	                Map<String, Object> uploadResult = cloudinary.uploader().upload(modifiedImageFile, params);
	                String url = uploadResult.get("url").toString();
	                // Save to the database or perform further actions    
	               
	                categories.setImage_url(url);
	                if (categories.getStatus() != null) {
	                    if (categories.getStatus()) {
	                        // Nếu trạng thái là true, hiển thị sản phẩm
	                    	categories.setStatus(true);
	                    } else {
	                        // Nếu trạng thái là false, ẩn sản phẩm
	                    	categories.setStatus(false);
	                    }
	                }
	                categoryRepo.save(categories);
	                // Delete the original image
	                savedFile.delete();

	                return "redirect:/admin/managerCategory";
	            }

	        } catch (Exception e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
		return "redirect:/admin/managerCategory";
	}
}
