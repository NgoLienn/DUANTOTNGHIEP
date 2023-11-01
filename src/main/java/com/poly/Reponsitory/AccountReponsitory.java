package com.poly.Reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.Entity.Account;

@Repository
public interface AccountReponsitory extends JpaRepository<Account, Integer> {
    @Query("Select u from Account u where u.UserName =?1")
    Account findByUsername(String Username);
    @Query("select u from Account u where u.token=?1")
    Account findToken(String token);
}
