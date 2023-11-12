package com.poly.Reponsitory;

import com.poly.Entity.Order_Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order_Items, Long> {
}
