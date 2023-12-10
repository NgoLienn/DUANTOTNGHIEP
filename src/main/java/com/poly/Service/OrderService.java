package com.poly.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Orders;
import com.poly.Reponsitory.OrdersReposiotry;

@Service
public class OrderService {
    @Autowired
    OrdersReposiotry ordersRepo;

    public long totalAmountDateNow(Date date) {
        long totalAmount = ordersRepo.totalAmountDateNow(date);
        if (Float.isNaN(totalAmount)) {
            return 0;
        } else {
            return totalAmount;
        }
    }

    public List<Orders> getAllCategories() {
        return ordersRepo.findAll();
    }

    @Transactional
    public List<Object> reportRevenue(int year) {
        return ordersRepo.reportRevenue(year);
    }

}
