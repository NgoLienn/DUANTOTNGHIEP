package com.poly.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/auth/signin")
    public String dangnhap(Model m){
        return "user/login";
    }

    @RequestMapping("/login/thanhcong")
    public String logintc(Model m){
        m.addAttribute("message","đăng nhập thành công");
        return "user/login";
    }

    @RequestMapping("/login/error")
    public String loginer(Model m){
        m.addAttribute("message","Sai thông tin Đăng Nhập"); //
        return "user/login";
    }
    @RequestMapping("/login/unauthorized")
    public String unauthoried(Model m){
        m.addAttribute("message","Không Có Quyền Truy Xuất");
        return "user/login";    //
    }
    @RequestMapping("/looff/success")
    public String looff(Model m){
        m.addAttribute("message","Bạn Đã Đăng Xuất");
        return "user/login";
    }
}
