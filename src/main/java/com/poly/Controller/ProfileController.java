package com.poly.Controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.poly.Entity.Account;
import com.poly.Entity.Carts;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.CartItemsRepository;
import com.poly.Reponsitory.CartRepository;
import com.poly.Service.AccountService;
import com.poly.Service.CustomOAuth2User;
import com.poly.Service.UserServiceGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user/profile")
public class ProfileController {

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

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemsRepository cartItemsRepo;

    @GetMapping
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



        return "user/profile";
    }

    @PostMapping
    public String changepass(Model m, Authentication authentication,
            @ModelAttribute("account") Account account, @RequestParam("uploadimage") MultipartFile file,
            @RequestParam("filess") String filess, HttpServletRequest httpServletRequest) {

        String users = "";
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User user = oauthToken.getPrincipal();
            users = user.getAttribute("email");
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
            users = authentication.getName();
        }

        Account acc = accountService.findByUsername(users);

        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "Images_FastFoodStore",
                "resource_type", "image");
        try {

            acc.setFullname(account.getFullname());
            acc.setPhone(account.getPhone());
            acc.setAddress(account.getAddress());

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
        return "redirect:/user/profile";
    }
}