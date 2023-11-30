package com.poly.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poly.Entity.Orders;
import com.poly.Reponsitory.OrdersReposiotry;

@Service
public class OrderService {
    OrdersReposiotry ordersRepo;

    public List<Orders> getAllCategories() {
        return ordersRepo.findAll();
    }
}
