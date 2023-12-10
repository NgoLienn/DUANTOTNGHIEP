package com.poly.Reponsitory;

import com.poly.Entity.Orders;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface OrdersReposiotry extends JpaRepository<Orders, Long> {

    @Query("select u from Orders u where u.orderID = ?1")
    Orders finByOrderItem(Long orderitemID);

    List<Orders> findByOrderTimeBetween(Date startDate, Date endDate);
    
    @Query("select u from Orders u where u.Account.UserName = ?1")
    List<Orders> finByUsernam(String username);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Orders o WHERE o.paymentDate = :date")
    public Long totalAmountDateNow(@Param("date") Date date);

    @Procedure("GetRevenueByMonth")
    List<Object> reportRevenue(int year);

}
