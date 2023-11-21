package com.poly.Service;

import com.poly.Entity.Account;
import com.poly.Entity.Blog;
import com.poly.Entity.Provider;
import com.poly.Reponsitory.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceGoogle {

    @Autowired
    private AccountReponsitory repo;


    public void processOAuthPostLogin(String username) {
        Account existUser = repo.findByUsername(username);

        if (existUser == null) {
            Account newUser = new Account();
            Blog blog = new Blog();
            blog.setBlogID(1);
//            newUser.setFullname(name);
            newUser.setBlog(blog);
            newUser.setUserName(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setActive(true);


            repo.save(newUser);

            System.out.println("Created new user: " + username);
        }

    }

}