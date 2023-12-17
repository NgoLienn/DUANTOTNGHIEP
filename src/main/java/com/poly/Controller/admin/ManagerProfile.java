package com.poly.Controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import com.poly.Service.UserServiceGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class ManagerProfile {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountReponsitory accountReponsitory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceGoogle userServiceGoogle;

    @Autowired
    Cloudinary cloudinary;

    @GetMapping("/profile")
    public String account(Model model,
                          Authentication authentication) {
        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();

        }
        Account account = accountService.findByUsername(users);
        model.addAttribute("account", new Account());
        model.addAttribute("profile", account);
        return "admin/profile";
    }

    @PostMapping("profile")
    public String changepass(Model m, Authentication authentication,
                             @ModelAttribute("account") Account account, @RequestParam("uploadimage") MultipartFile file,
                             @RequestParam("filess") String filess, HttpServletRequest httpServletRequest) {

        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();//
        }

        Account acc = accountService.findByUsername(users);

        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");
        try {
            String fullnamee = httpServletRequest.getParameter("Fullname");
            String Phone = httpServletRequest.getParameter("Phone");
            account.setFullname(fullnamee);
            account.setPhone(Phone);
            acc.setFullname(account.getFullname());
            acc.setPhone(account.getPhone());

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
            acc.setAvata(temp);
            accountReponsitory.save(acc);
        } catch (Exception e) {

        }
        return "redirect:/admin/profile";
    }
}
