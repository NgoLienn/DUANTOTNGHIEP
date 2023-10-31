package com.poly.Service;

import java.util.List;

import com.poly.Entity.Authority;
import com.poly.Entity.Blog;
import com.poly.Reponsitory.AuthorityResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poly.Entity.Account;
import com.poly.Entity.Roles;
import com.poly.Reponsitory.AccountReponsitory;

@Service
public class AccountService {
    @Autowired
    AccountReponsitory accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthorityResponsitory authorityResponsitory;
    public List<Account> getAllAccount() {
        return accountRepository.findAll();

    }

    public Account findByUsername(String Username) {
        return accountRepository.findByUsername(Username);
    }
    public Account saveAccount(Account account){
        account.setUserName(account.getUserName());
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        System.out.println(account.getUserName());
        System.out.println(encodedPassword);
        account.generateActivationToken();
        Blog blog =new Blog();
        blog.setBlogID(1);
        account.setPassword(encodedPassword);
        account.setBlog(blog);
        account.setActive(false);
        accountRepository.save(account);
        if (account.getAuthorities() == null || account.getAuthorities().isEmpty()) {
            // tạo vai trò khi tài khoản mới
                Authority authority = new Authority();
                authority.setAccount(account);
                Roles role = new Roles();
                role.setId("1");
                authority.setRole(role);
                authorityResponsitory.save(authority);
        }
        account.setActive(false);
        return account;
    }
    public Account SaveAccountActive(Account account) {
        account.setActive(true);
        account.setToken(null);
        account =accountRepository.save(account);
        return account;
    }
}