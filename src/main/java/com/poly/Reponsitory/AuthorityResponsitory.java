package com.poly.Reponsitory;

import com.poly.Entity.Account;
import com.poly.Entity.Authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityResponsitory extends JpaRepository<Authority, Integer> {
    @Query("Select a from Authority a where a.account.active = true")
    List<Authority> findAllAcount();


    @Query("Select a from Authority a where a.account.UserName =:username")
    Authority findByUser(String username);

    @Query("Select a from Authority a where a.account.Fullname like %?1%")
    List<Authority> findByFullname(String fullname);

    @Query("select u from Authority u where u.account.UserName=?1")
    Authority findByUserName(String UserName);
}
