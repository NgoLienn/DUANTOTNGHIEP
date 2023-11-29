package com.poly.Reponsitory;

import com.poly.Entity.Orders;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersReposiotry extends JpaRepository<Orders, Long> {

    @Query("select u from Orders u where u.orderID = ?1")
    Orders finByOrderItem(Long orderitemID);

    @Query("select u from Orders u where u.Account.UserName = ?1")
    List<Orders> finByUsernam(String username);

    List<Orders> findByOrderTimeBetween(Date startDate, Date endDate);
}
