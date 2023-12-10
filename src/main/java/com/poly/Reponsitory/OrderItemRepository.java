package com.poly.Reponsitory;

import com.poly.Entity.Order_Items;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<Order_Items, Long> {

    @Query("select u from Order_Items u where u.Orders.orderID = ?1")
    List<Order_Items> finByOrderItem(Long orderid);

    @Query("SELECT u.Name, SUM(u.Quantity) AS SoLuong, MAX(u.Price) AS GiaGoc, SUM(u.Subtotal) AS TongTien FROM Order_Items u GROUP BY u.Name ORDER BY SoLuong DESC")
    List<Object> topProducts();
}
