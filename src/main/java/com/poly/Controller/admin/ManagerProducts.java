package com.poly.Controller.admin;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.poly.Entity.Page;

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
import com.poly.Entity.Categories;
import com.poly.Entity.Order_Items;
import com.poly.Entity.Products;
import com.poly.Entity.Reviews;
import com.poly.Reponsitory.CategoryRepository;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.CategoryService;
import com.poly.Service.ProductService;

@Controller
@RequestMapping("/admin")
public class ManagerProducts {
    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    Cloudinary cloudinary;

    private final int pageSize = 6;

    @GetMapping("/managerProducts")
    public String ViewProfile(Model model, @RequestParam(defaultValue = "1") int page,
            @RequestParam(value = "query", defaultValue = "") String query) {
        List<Products> listProduct;

        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("admincategory", categoryList);

        model.addAttribute("newProduct", new Products());

        model.addAttribute("true", true);

        // for (Products product : listProduct) {
        // int productId = product.getId();
        // Products listProductt = productRepo.getById(productId);
        // listProductt.setStatus_prod(true);
        // productRepo.save(productId);

        // }

        // tìm kiếm sản phẩm
        if (query.equals("")) {
            listProduct = productRepo.findAll();
        } else {
            listProduct = productService.searchProducts(query);

        }

        // Sắp xếp danh sách sản phẩm theo thời gian tạo (createAt) chỉ khi createAt
        // không null
        listProduct.sort((p1, p2) -> {
            if (p1.getCreate_at() != null && p2.getCreate_at() != null) {
                return p2.getCreate_at().compareTo(p1.getCreate_at());
            } else if (p1.getCreate_at() == null && p2.getCreate_at() == null) {
                return 0;
            } else if (p1.getCreate_at() == null) {
                return 1;
            } else {
                return -1;
            }
        });

        model.addAttribute("query", query);
        model.addAttribute("ListProduct", listProduct);
        // end

        // phân trang sản phẩm
        int totalProduct = listProduct.size();
        int totalPages = (int) Math.ceil(totalProduct / (double) pageSize);

        // Lấy danh sách tài khoản trên trang hiện tại
        int start = (page - 1) * pageSize;

        int end = Math.min(start + pageSize, totalProduct);
        List<Products> productsOnPage = listProduct.subList(start, end);

        // Đưa thông tin về dữ liệu và phân trang vào Model
        Page productPage = new Page();
        productPage.setProductsList(productsOnPage);
        productPage.setTotalPages(totalPages);
        productPage.setCurrentPage(page);
        model.addAttribute("productPage", productPage);
        // end

        return "/admin/product";
    }

    @GetMapping("/managerProducts/edit/{productId}")
    public String edit(Model model, @PathVariable("productId") int productId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(value = "query", defaultValue = "") String query) {

        model.addAttribute("productIdd", productId);

        List<Products> listProduct;

        Products editProduct = productService.getProductById(productId);

        model.addAttribute("false", false);

        model.addAttribute("newProduct", editProduct);

        List<Categories> categoryList = categoryService.getAllCategories();
        model.addAttribute("admincategory", categoryList);
        // Thay đổi view name nếu cần

        // tìm kiếm sản phẩm
        if (query.equals("")) {
            listProduct = productRepo.findAll();
        } else {

            listProduct = productService.searchProducts(query);
        }
        model.addAttribute("query", query);
        model.addAttribute("ListProduct", listProduct);
        // end

        // phân trang sản phẩm
        int totalProduct = listProduct.size();
        int totalPages = (int) Math.ceil(totalProduct / (double) pageSize);

        // Lấy danh sách tài khoản trên trang hiện tại
        int start = (page - 1) * pageSize;

        int end = Math.min(start + pageSize, totalProduct);
        List<Products> productsOnPage = listProduct.subList(start, end);

        // Đưa thông tin về dữ liệu và phân trang vào Model
        Page productPage = new Page();
        productPage.setProductsList(productsOnPage);
        productPage.setTotalPages(totalPages);
        productPage.setCurrentPage(page);
        model.addAttribute("productPage", productPage);
        // end

        return "/admin/product";

    }

    @PostMapping("/managerProducts/add")
    public String addProduct(@ModelAttribute("newProduct") Products product,
            BindingResult bindingResult,
            @RequestParam("category") Long category,
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
            Categories categories = new Categories();
            categories.setCategoryId(category);
            product.setCategoryId(categories);
            product.setImage(url);
            product.setStatus_prod(true);
            if (product.getStatus_prod() != null) {
                if (product.getStatus_prod()) {
                    // Nếu trạng thái là true, hiển thị sản phẩm
                    product.setStatus_prod(false);
                } else {
                    // Nếu trạng thái là false, ẩn sản phẩm
                    product.setStatus_prod(true);
                }
            }
            productRepo.save(product);

            // Delete the original image
            savedFile.delete();

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return "redirect:/admin/managerProducts";
    }

    @PostMapping("/managerProducts/update/{id}")
    public String updateProduct(@ModelAttribute @Valid Products product, BindingResult bindingResult,
            @PathVariable("id") Long id,
            @RequestParam("filess") String filess, @RequestParam("uploadimage") MultipartFile file,
            Model model) {
        // Cập nhật thông tin sản phẩm vào cơ sở dữ liệu
        Products existingProduct = productRepo.findById(product.getId()).orElse(null);

        Map params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");

        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrices(product.getPrices());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCreate_at(product.getCreate_at());
            existingProduct.setDescription_an(product.getDescription_an());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStatus_prod(true);
            // Kiểm tra trạng thái mới của sản phẩm để ẩn hoặc hiển thị
            if (existingProduct.getStatus_prod() != null) {
                if (existingProduct.getStatus_prod()) {
                    // Nếu trạng thái là true, hiển thị sản phẩm
                    existingProduct.setStatus_prod(false);
                } else {
                    // Nếu trạng thái là false, ẩn sản phẩm
                    existingProduct.setStatus_prod(true);
                }
            }
            existingProduct.setCategory(product.getCategory());
            // Thêm các trường còn lại tương ứng

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
            existingProduct.setImage(temp);
            productRepo.save(existingProduct);
        }
        return "redirect:/admin/managerProducts";
    }

    @PostMapping("/managerProducts/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") int productId) {
        productService.delete(productId);
        return "redirect:/admin/managerProducts";
    }

}
