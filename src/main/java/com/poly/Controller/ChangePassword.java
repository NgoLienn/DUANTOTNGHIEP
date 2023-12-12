package com.poly.Controller;

import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class ChangePassword {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountReponsitory accountReponsitory;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/changepassword")
    public String Changepassword(){

        return "user/changepassword";

    }
    @PostMapping("/changepassword")
    public String Changepassword2(Model model, HttpServletRequest httpServletRequest,
                                  @RequestParam("Password")String Password,
                                  @RequestParam("Passwordnew")String Passwordnew){
        String username = httpServletRequest.getRemoteUser();
        Account account = accountReponsitory.findByUsername(username);
        if(passwordEncoder.matches(Password,account.getPassword())){
            String encodedPassword = passwordEncoder.encode(Passwordnew);
            account.setPassword(encodedPassword);
            accountReponsitory.save(account);
            model.addAttribute("message","đổi mật khẩu thành công");
            return "/user/changepassword";

        }else{
            model.addAttribute("message","Mật khẩu không khớp");
            return "/user/changepassword";
        }
    }

}
