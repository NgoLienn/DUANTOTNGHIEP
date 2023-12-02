package com.poly.Service;

import com.poly.Entity.*;
import com.poly.Reponsitory.AccountReponsitory;
import com.poly.Reponsitory.AuthorityResponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceGoogle {
    @Autowired
    AuthorityResponsitory authorityResponsitory;
    @Autowired
    private AccountReponsitory repo;


    public void processOAuthPostLogin(String username) {
        Account existUser = repo.findByUsername(username);

        if (existUser == null) {
            Account newUser = new Account();
            Blog blog = new Blog();
            blog.setBlogID(1);
            newUser.setBlog(blog);
            newUser.setUserName(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setActive(true);
            Roles roles = new Roles();
            roles.setId("2");
            Authority authority = new Authority();
            authority.setAccount(newUser);
            authority.setRole(roles);
            repo.save(newUser);
            authorityResponsitory.save(authority);
        }

    }

}