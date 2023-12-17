package com.poly;

import com.poly.Entity.Orders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
public class FastFoodStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastFoodStoreApplication.class, args);

        Orders entity = new Orders();

        // Lấy ngày và giờ hiện tại
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        entity.setOrderTime(currentTimestamp);

        // Định dạng và hiển thị ngày và giờ
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}