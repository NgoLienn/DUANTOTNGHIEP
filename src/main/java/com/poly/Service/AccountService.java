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
    AccountReponsitory accountRepository; //
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthorityResponsitory authorityResponsitory;

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public List<Authority> findAllAccount() {
        return authorityResponsitory.findAllAcount();
    }

    public Account findByUsername(String Username) {
        return accountRepository.findByUsername(Username);
    }

    public Authority findByUser(String Username) {
        return authorityResponsitory.findByUser(Username);
    }

    public List<Authority> findFullName(String fullname) {
        return authorityResponsitory.findByFullname(fullname);
    }

    public Account saveAccount(Account account) {
        account.setUserName(account.getUserName());
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        System.out.println(account.getUserName());
        System.out.println(encodedPassword);
        account.generateActivationToken();
        Blog blog = new Blog();
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
            role.setId("2");
            authority.setRole(role);
            authorityResponsitory.save(authority);
        }
        account.setActive(false);
        return account;
    }

    public Account addAccount(Account account, String roleId) {
        account.setUserName(account.getUserName());
        String encodedPassword = passwordEncoder.encode(account.getPassword());
        account.generateActivationToken();
        Blog blog = new Blog();
        blog.setBlogID(1);
        account.setPassword(encodedPassword);
        account.setBlog(blog);
        account.setActive(true);
        accountRepository.save(account);
        if (account.getAuthorities() == null || account.getAuthorities().isEmpty()) {
            // tạo vai trò khi tài khoản mới
            Authority authority = new Authority();
            authority.setAccount(account);
            Roles role = new Roles();
            role.setId(roleId);
            authority.setRole(role);
            authorityResponsitory.save(authority);
        }
        return account;
    }

    public Account SavePass(Account account) {
        account.setResetToken(null);
        return accountRepository.save(account);
    }

    public Account createToken(Account account) {
        account.ResetToken();
        return accountRepository.save(account);

    }

    public Account SaveAccountActive(Account account) {
        account.setActive(true);
        account.setToken(null);
        account = accountRepository.save(account);
        return account;
    }

    public int totalAccount() {
        return accountRepository.countAccount();
    }

    public void deleteAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        account.setActive(false);
        accountRepository.save(account);
    }

    public void updateAccount(String fullname, String email, String password, String phone, String address,
            String avatar) {
        Account account = accountRepository.findByUsername(email);
        account.setFullname(fullname);
        account.setUserName(email);
        account.setPassword(password);
        account.setPhone(phone);
        account.setAddress(address);
        if (avatar != "") {
            account.setAvata(avatar);
        }
        accountRepository.save(account);
    }

    public void setRole(String roleId, String username) {
        Authority authority = authorityResponsitory.findByUser(username);
        Roles role = new Roles();
        role.setId(roleId);
        authority.setRole(role);
        authorityResponsitory.save(authority);
    }
}