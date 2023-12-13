package com.poly.Reponsitory;

import com.poly.Entity.Order_Items;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<Order_Items, Long> {

    @Query("select u from Order_Items u where u.Orders.orderID = ?1")
    List<Order_Items> finByOrderItem(Long orderid);

    @Query("SELECT\r\n" + //
            "    u.Name,\r\n" + //
            "    SUM(u.Quantity) AS SoLuong,\r\n" + //
            "    MAX(u.Price) AS GiaGoc,\r\n" + //
            "    SUM(u.Subtotal) AS TongTien\r\n" + //
            "FROM\r\n" + //
            "    Order_Items u\r\n" + //
            "GROUP BY\r\n" + //
            "    u.Name\r\n" + //
            "HAVING\r\n" + //
            "    SUM(u.Quantity) > 4\r\n" + //
            "ORDER BY\r\n" + //
            "    SoLuong DESC")
    List<Object> topProducts();
}
