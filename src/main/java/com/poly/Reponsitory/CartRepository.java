package com.poly.Reponsitory;

import com.poly.Entity.Carts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository <Carts, Long> {
    @Query("select u from Carts u where u.Account.accountID=?1")
    Carts findByCartsUsername(int accountID);
}
