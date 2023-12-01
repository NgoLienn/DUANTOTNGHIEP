package com.poly.Controller.admin;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;
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
import com.poly.Entity.Image_product;
import com.poly.Entity.Products;
import com.poly.Reponsitory.ImageProductRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;
import com.poly.Service.ImageProductService;

@Controller
@RequestMapping("/admin")
public class ManagerImageProduct {

    @Autowired
    ImageProductRepository imageProductRepo;

    @Autowired
    ImageProductService imageProductService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryService categoryService;

    @Autowired
    Cloudinary cloudinary;

    @GetMapping("/managerImageProduct")
    public String ViewCategory(Model model) {

        List<Image_product> imageProduct = imageProductRepo.findAll();
        model.addAttribute("imageProduct", imageProduct);

        List<Products> products = productRepo.findAll();
        model.addAttribute("products", products);

        model.addAttribute("newImageProduct", new Image_product());
        return "admin/image_product";
    }

    @GetMapping("/managerImageProducts/edit/{imageproductId}")
    public String edit(Model model, @PathVariable("imageproductId") Long imageproductId) {

        model.addAttribute("imageproductIdd", imageproductId);

        List<Products> products = productRepo.findAll();
        model.addAttribute("products", products);

        List<Image_product> imageProduct = imageProductRepo.findAll();
        model.addAttribute("imageProduct", imageProduct);

        Image_product editImage = imageProductService.getImageProductById(imageproductId);
        model.addAttribute("newImageProduct", editImage);
        return "admin/image_product";
    }

    @PostMapping("/managerImageProducts/update/{id}")
    public String updateImageProduct(@ModelAttribute @Valid Image_product updatedImageProduct,
            BindingResult bindingResult,
            @PathVariable("id") Long id,
            @RequestParam("filess") String filess,
            @RequestParam("uploadimage") MultipartFile file,
            Model model) {

        if (bindingResult.hasErrors()) {
            // Trả về trang hoặc thông báo lỗi phù hợp
            return "admin/image_product";
        }

        // Tìm Image_product trong cơ sở dữ liệu dựa trên ID
        Optional<Image_product> optionalImageProduct = imageProductRepo.findById(id);
        if (optionalImageProduct.isPresent()) {
            Image_product image_product = optionalImageProduct.get();

            // Cập nhật các thông tin cần thiết cho image_product từ updatedImageProduct
            image_product.setAlt_Text(updatedImageProduct.getAlt_Text());

            // Cập nhật productId cho image_product
            Products product = productRepo.findById(updatedImageProduct.getProductId().getId()).orElse(null);
            if (product != null) {
                image_product.setProductId(product);
            }

            // Tiến hành upload file và cập nhật URL nếu có file được chọn
            String temp = null;
            if (file != null && !file.isEmpty()) {
                try {
                    Map<String, Object> params = ObjectUtils.asMap(
                            "folder", "Images_FastFoodStore",
                            "resource_type", "image");
                    Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
                    String url = uploadResult.get("url").toString();
                    temp = url;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                temp = filess;
            }
            image_product.setUrl_Img(temp);

            // Lưu thông tin cập nhật vào cơ sở dữ liệu
            imageProductRepo.save(image_product);
        } else {
            // Xử lý trường hợp không tìm thấy Image_product có ID tương ứng
            // Hoặc thực hiện các hành động khác nếu cần
        }

        return "redirect:/admin/managerImageProduct";
    }

    @PostMapping("/managerImageProducts/add")
    public String addProduct(@ModelAttribute("newImageProduct") Image_product imageproduct,
            BindingResult bindingResult,
            @RequestParam("uploadimage") MultipartFile file, Model model) {

        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");

        try {
            // Your existing code to save the original file
            String baseDir = System.getProperty("user.dir");
            String folderPath = baseDir + File.separator + "src" + File.separator + "main" + File.separator
                    + "resources"
                    + File.separator + "static" + File.separator + "assets" + File.separator + "images" + File.separator
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
            imageproduct.setUrl_Img(url);
            imageProductRepo.save(imageproduct);

            // Delete the original image
            savedFile.delete();

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return "redirect:/admin/managerImageProduct";
    }

    @PostMapping("/managerImageProducts/delete/{iamgeproductId}")
    public String deleteProduct(@PathVariable("iamgeproductId") Long iamgeproduct) {
        imageProductService.delete(iamgeproduct);
        return "redirect:/admin/managerImageProduct";
    }

}
