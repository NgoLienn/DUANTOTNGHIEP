package com.poly.Controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;

public class logingoogle {
    @GetMapping("/login-google")
    public String login() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/user-info")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof DefaultOAuth2User) {
            DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
            // Lấy thông tin người dùng từ oauthUser và lưu vào CSDL
            String email = (String) oauthUser.getAttribute("email");
            // Tiếp tục xử lý thông tin người dùng theo yêu cầu của bạn
        }
        return "redirect:/home"; // Điều hướng tới trang home hoặc trang mong muốn khác
    }
}
