package com.poly.Controller;

import com.poly.Service.MailServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    AccountService accountService;
    @Autowired
    MailServiceIMPL mailerService;
    @Autowired
    AccountReponsitory accountReponsitory;

    @GetMapping()
    public String ViewProfile(Model model) {
        model.addAttribute("account",new Account());
        return "user/register";

    }
    @PostMapping
    public String signupaccount(Model m ,@ModelAttribute("account") Account acc){
        System.out.println(acc.getUserName());
        Account account =accountReponsitory.findByUsername(acc.getUserName());//kiem tra tai khoan da duoc su dung
        if(account!=null){
            if(account.getActive()==true){
                m.addAttribute("message","Tài Khoản đã được sử dụng");
                return "user/register";
            }else{
                accountService.saveAccount(acc);
                String activationUrl = "http://localhost:8080/register/activate?token=" + acc.getToken();
                mailerService.send(account.getUserName(),"Kích Hoạt Tài Khoản","Xin Chào  Chúng tôi đã nhận được yêu cầu đăng nhập vào CrouseOnline bằng địa chỉ email này. Nếu bạn muốn đăng nhập bằng tài khoản "+account.getUserName()+" của mình, hãy nhấp vào liên kết:"+"<a href='" + activationUrl + "'>tại đây</a>");
                m.addAttribute("message","Vui Lòng Kiểm Tra Mail Để kích hoạt tài khoản");
                return "user/register";
            }
        } //
        accountService.saveAccount(acc);
        String activationUrl = "http://localhost:8080/register/activate?token=" + acc.getToken();
        mailerService.send(acc.getUserName(),"Kích Hoạt Tài Khoản","Xin Chào  Chúng tôi đã nhận được yêu cầu đăng nhập vào CrouseOnline bằng địa chỉ email này. Nếu bạn muốn đăng nhập bằng tài khoản "+acc.getUserName()+" của mình, hãy nhấp vào liên kết:"+"<a href='" + activationUrl + "'>tại đây</a>");
        m.addAttribute("message","Vui Lòng Kiểm Tra Mail Để kích hoạt tài khoản");

        return "user/register";
    }

    @GetMapping("/activate")
    public String activeaccount(Model m , @RequestParam("token")String token){
        Account acc =accountReponsitory.findbytoken(token);
        if(acc !=null){
            accountService.SaveAccountActive(acc);
            m.addAttribute("message","Kích Hoạt Tài Khoản Thành Công");
            return "redirect:/auth/signin";
        }else{
            m.addAttribute("message", "Mã Token kích hoạt không hợp lệ");
            return "user/register";
        }

    }
}




