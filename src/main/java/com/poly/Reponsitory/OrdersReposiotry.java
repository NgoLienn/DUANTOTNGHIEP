package com.poly.Reponsitory;

import com.poly.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersReposiotry extends JpaRepository<Orders,Long> {

}
