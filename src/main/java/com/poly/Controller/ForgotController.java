package com.poly.Controller;


import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Service.AccountService;
import com.poly.Service.MailServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgotController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountService accountService;
    @Autowired
    MailServiceIMPL mailServiceIMPL;
    @Autowired
    AccountReponsitory accountReponsitory;

    @RequestMapping("/forgot")
    public String forgotpass(Model m){
        return "user/forgot_pass";

    }
    @PostMapping("/account/quen-mat-khau")
    public String guimail(Model m, @RequestParam("email")String email){
        Account acc = accountService.findByUsername(email);

        if(acc != null){
            if(!acc.getActive()){
                m.addAttribute("message","tài khoản không tồn tại");
            }else{
                acc=accountService.createToken(acc);
                String token=acc.getResetToken();
                String activationUrl = "http://localhost:8080/quenmk?token=" +token;
                mailServiceIMPL.send( acc.getUserName(),"Quên Mật Khẩu Từ NVA3","Xin Chào  Chúng tôi đã nhận được yêu cầu Quên Mật Khẩu vào NV3 bằng địa chỉ email này. Nếu bạn muốn Thay Đổi Mật Khẩu bằng tài khoản "+acc.getUserName()+" của mình, hãy nhấp vào liên kết:"+"<a href='" + activationUrl + "'>tại đây</a>");
                m.addAttribute("message","Vui lòng kiểm tra email");
                return "user/forgot_pass";
            }
        }
        m.addAttribute("message","Tài khoản không tồn tại");
        return "user/forgot_pass";
    }
    @RequestMapping("quenmk")
    public String quenmkac(Model m, @RequestParam("token") String token){

        m.addAttribute("token", token);
        return "user/confirm_password";
    }

    @PostMapping("/thay-doi-mat-khau")
    public String quenmk(Model m, @RequestParam("token") String token, @RequestParam("password") String password) {
        Account acc = accountReponsitory.findToken(token);
        System.out.println(token);
        if (acc == null) {
            System.out.println("1");
            String script = "<script>alert('Mã Token Đã Hết Hạn');</script>";
            m.addAttribute("script", script);
            return "user/confirm_password";
        } else {
            String encodedPassword = passwordEncoder.encode(password);
            acc.setPassword(encodedPassword);
            acc.setResetToken(null);
            accountReponsitory.save(acc);
            String script = "<script>alert('mật khẩu đã được thay đổi');</script>";
            m.addAttribute("script", script);
            return "redirect:/auth/signin";
        }
    }


}