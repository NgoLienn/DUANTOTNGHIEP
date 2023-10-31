package com.poly.Service;
import com.poly.Entity.Account;
import com.poly.Reponsitory.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    AccountReponsitory accountDAO;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Account account = accountDAO.findByUsername(username);
            if (!account.getActive()) {
                throw new UsernameNotFoundException(username + " is not active!");

            }
            String storedPassword = account.getPassword();
            return User.withUsername(username)
                    .password(storedPassword)
                    .roles(account.getAuthorities().stream()
                            .map(au -> au.getRole().getId())
                            .collect(Collectors.toList())
                            .toArray(new String[0]))
                    .build();

        } catch (Exception e) {
            throw new UsernameNotFoundException(username + " not found!");
        }
    }


}
