package com.poly.Controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.utils.StringUtils;
import com.poly.Entity.Account;
import com.poly.Entity.Authority;
import com.poly.Entity.Page;
import com.poly.Entity.PageAccount;
import com.poly.Entity.Products;
import com.poly.Service.AccountService;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ManagerAccount {

    @Autowired
    AccountService accountService;

    @Autowired
    Cloudinary cloudinary;

    private final int pageSize = 6;

    @GetMapping("/managerAccount/{key}")
    public String ViewAccount(Model model, @RequestParam(defaultValue = "1") int page, @PathVariable String key) {

        List<Authority> authority;

        if (key.equals("list")) {
            // Lấy danh sách tài khoản
            authority = accountService.findAllAccount();
        } else {
            // Lấy danh sách tài khoản dựa trên key
            authority = accountService.findFullName(key);
        }

        // phân trang sản phẩm
        int totalAccount = authority.size();
        int totalPages = (int) Math.ceil(totalAccount / (double) pageSize);

        // Lấy danh sách tài khoản trên trang hiện tại
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalAccount);
        List<Authority> authorityOnPage = authority.subList(start, end);
        List<Authority> reversedAuthority = new ArrayList<>(authorityOnPage);
        Collections.reverse(reversedAuthority);

        // Đưa thông tin về dữ liệu và phân trang vào Model
        PageAccount pageAccount = new PageAccount();
        pageAccount.setListAuthority(reversedAuthority);
        pageAccount.setTotalPages(totalPages);
        pageAccount.setCurrentPage(page);
        model.addAttribute("authorityPage", pageAccount);

        return "admin/tables";
    }

    @ResponseBody
    @GetMapping("/edit-account/{username}")
    public Authority accountEdit(@PathVariable String username) {
        return accountService.findByUser(username);
    }

    @ResponseBody
    @GetMapping("/delete/{username}")
    public String accountDelete(@PathVariable String username, HttpServletRequest httpServletRequest) {

        // Tìm tài khoản
        String myAccount = httpServletRequest.getRemoteUser();
        Authority account = accountService.findByUser(myAccount);

        if (account.getAccount().getUserName().equals(username)) {
            return "Không được xóa tài khoản của mình";
        } else {
            accountService.deleteAccount(username);
            return "Xóa thành công tài khoản";
        }
    }

    @ResponseBody
    @PostMapping("/update")
    public String updateAccount(@RequestParam("fullname") String fullname, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("role") String role, @RequestParam(name = "picture", required = false) MultipartFile file) {

        Map params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");

        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
                String url = uploadResult.get("url").toString();
                accountService.updateAccount(fullname, email, password, phone, address, url);
                accountService.setRole(role, email);
                return "Sửa thông tin thành công";
            } catch (Exception e) {
                return "Lỗi";
            }
        } else {
            accountService.updateAccount(fullname, email, password, phone, address, "");
            accountService.setRole(role, email);
            return "Sửa thông tin thành công";
        }

    }

    @ResponseBody
    @PostMapping("/addAccount")
    public String addAccount(@RequestParam("fullname") String fullname, @RequestParam("email") String email,
            @RequestParam("password") String password, @RequestParam("phone") String phone, @RequestParam("address") String address,
            @RequestParam("role") String role, @RequestParam(name = "picture", required = false) MultipartFile file) {
            
        Map params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");

        Account acc = accountService.findByUsername(email);
        if (acc != null) {
            return "Tài khoản đã tồn tại!";
        }

        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
                String url = uploadResult.get("url").toString();

                Account account = new Account();
                account.setFullname(fullname);
                account.setUserName(email);
                account.setPassword(password);
                account.setPhone(phone);
                account.setAvata(url);
                account.setAddress(address);
                accountService.addAccount(account, role);
                return "Thêm thành công";
            } catch (Exception e) {
                return "Lỗi";
            }
        } else {
            Account account = new Account();
            account.setFullname(fullname);
            account.setUserName(email);
            account.setPassword(password);
            account.setPhone(phone);
            account.setAddress(address);
            accountService.addAccount(account, role);
            return "Thêm thành công";
        }

    }

}
