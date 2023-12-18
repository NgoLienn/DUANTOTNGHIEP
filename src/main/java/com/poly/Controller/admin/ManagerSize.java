package com.poly.Controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.Entity.PageSize;
import com.poly.Entity.Products;
import com.poly.Entity.Size;
import com.poly.Entity.Size_Product;
import com.poly.Entity.TableSize;
import com.poly.Reponsitory.ProductRepository;
import com.poly.Service.ProductService;
import com.poly.Service.SizeService;

@Controller
@RequestMapping("/admin")
public class ManagerSize {

    @Autowired
    SizeService sizeService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    ProductService productService;
    private final int pageSize = 8;

    @GetMapping("/managerSize/{key}")
    public String ViewSize(Model model, @RequestParam(defaultValue = "1") int page, @PathVariable String key) {
        // Lấy danh sách tài khoản
        List<Size_Product> sizes;
        if (key.equals("list")) {
            sizes = sizeService.findAllSizes();
        } else {
            sizes = sizeService.findProductsName(key);
        }

        List<Products> products = productRepo.findAll();

        model.addAttribute("products", products);

        // phân trang sản phẩm
        int totalAccount = sizes.size();
        int totalPages = (int) Math.ceil(totalAccount / (double) pageSize);

        // Lấy danh sách tài khoản trên trang hiện tại
        int start = (page - 1) * pageSize;

        int end = Math.min(start + pageSize, totalAccount);
        List<Size_Product> sizeOnPage = sizes.subList(start, end);

        // Đưa thông tin về dữ liệu và phân trang vào Model
        PageSize pageSize = new PageSize();
        pageSize.setListSize(sizeOnPage);
        pageSize.setTotalPages(totalPages);
        pageSize.setCurrentPage(page);

        model.addAttribute("sizePage", pageSize);

        return "admin/size";
    }

    @ResponseBody
    @GetMapping("/findSizeId/{id}")
    public Object ViewSize(@PathVariable Long id) {
        return sizeService.findSizeProduct(id);
    }

    @ResponseBody
    @GetMapping("/addSize/{nameProduct}/{nameSize}/{price}")
    public String addSize(@PathVariable String nameProduct, @PathVariable String nameSize, @PathVariable float price) {

        Products products = productService.findByNameProduct(nameProduct);
        if (products != null) {
            TableSize tableSize = sizeService.findTableSize(nameSize);
            if (tableSize != null) {
                // add size vào trước
                Size size = sizeService.addSize(products, tableSize);
                // add giá và size
                sizeService.addSize_Product(size, price);
            } else {
                // add size mới vào
                TableSize tableSizeNew = sizeService.addTableSize(nameSize);

                // add size vào trước
                Size size = sizeService.addSize(products, tableSizeNew);
                // add giá và size
                sizeService.addSize_Product(size, price);
            }
            return "Thêm thành công";
        } else {
            return "Chưa có sản phẩm này để thêm size!";
        }
    }

    @ResponseBody
    @GetMapping("/updateSize/{nameProduct}/{nameSize}/{price}")
    public String updateSize(@PathVariable String nameProduct, @PathVariable String nameSize,
            @PathVariable float price) {

        Products products = productService.findByNameProduct(nameProduct);
        if (products != null) {

            // tìm tableSize
            TableSize tableSize = sizeService.findTableSize(nameSize);
            if (tableSize == null) {
                TableSize tableSizeNew = sizeService.addTableSize(nameSize);

                Size size = sizeService.addSize(products, tableSizeNew);

                // tìm size_product
                Size_Product size_Product = sizeService.addSize_Product(size, price);

                sizeService.deleteSizeProduct(size_Product);

                // cập nhật giá
                sizeService.updateSize_Product(size_Product, price);
                return "Sửa thành công";
            } else {
                // tìm size
                Size size = sizeService.findTableSizeIdAndProductId(products.getProductId(), tableSize.getId());

                // tìm size_product
                Size_Product size_Product = sizeService.findBySizeProduct(size.getSizeID());

                // cập nhật giá
                sizeService.updateSize_Product(size_Product, price);
                return "Sửa thành công";
            }
        } else {
            return "Chưa có sản phẩm này để sửa!";
        }
    }

    @ResponseBody
    @GetMapping("/deleteSize/{nameProduct}/{nameSize}")
    public String deleteSize(@PathVariable String nameProduct, @PathVariable String nameSize) {

        // Tìm sản phẩm
        Products products = productService.findByNameProduct(nameProduct);

        // tìm tableSize
        TableSize tableSize = sizeService.findTableSize(nameSize);

        // tìm size
        Size size = sizeService.findTableSizeIdAndProductId(products.getProductId(), tableSize.getId());

        // tìm size_product
        Size_Product size_Product = sizeService.findBySizeProduct(size.getSizeID());

        // xóa trong 2 bảng
        sizeService.deleteSizeProduct(size_Product);

        // xóa bảng này sau
        sizeService.deleteSize(size);

        return "Xóa thành công";

    }
}
