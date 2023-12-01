package com.poly.Service;

import java.io.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.Entity.Order_Items;
import com.poly.Entity.Orders;
import com.poly.Reponsitory.OrderItemRepository;
import com.poly.Reponsitory.OrdersReposiotry;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ExcelExportService {

    @Autowired
    private OrdersReposiotry ordersRepository;

    @Autowired
    private OrderItemRepository orderItemRepo;

    // xuất excel cho tất cả đơn hàng
    public byte[] exportAllOrdersToExcel() {
        List<Orders> ordersList = ordersRepository.findAll(); // Lấy danh sách đơn hàng từ cơ sở dữ liệu

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Orders");

            // Tạo header cho các cột trong file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Đơn");
            headerRow.createCell(1).setCellValue("Tên");
            headerRow.createCell(2).setCellValue("Địa Chỉ");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Số Điện Thoại");
            headerRow.createCell(5).setCellValue("Trạng Thái Đơn");
            headerRow.createCell(6).setCellValue("Số Lượng Sản Phẩm Trong Đơn");
            headerRow.createCell(7).setCellValue("Tổng Giá Sản Phẩm Trong Đơn");
            headerRow.createCell(8).setCellValue("Phương Thức Thanh Toán");
            headerRow.createCell(9).setCellValue("Thời Gian Đặt Hàng");

            // Thêm các cột khác tùy ý

            int rowNum = 1;
            for (Orders order : ordersList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrderID());
                row.createCell(1).setCellValue(order.getAccount().getFullname());
                row.createCell(2).setCellValue(order.getAccount().getAddress());
                row.createCell(3).setCellValue(order.getAccount().getUserName());
                row.createCell(4).setCellValue(order.getAccount().getPhone());
                row.createCell(5).setCellValue(order.getStatus().getName());
                row.createCell(6).setCellValue(order.getOrderitem().size());
                row.createCell(7).setCellValue(order.getTotalAmount());
                row.createCell(8).setCellValue(order.getPaymentMethod());
                row.createCell(9).setCellValue(order.getOrderTime().toString());
                // Điền thông tin các cột khác tùy ý
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // end

    // xuất excel cho tất cả đơn hàng theo thời gian được chọn
    public byte[] exportOrdersToExcel(Date startDate, Date endDate) {
        // List<Orders> ordersList = ordersRepository.findAll();

        List<Orders> ordersList;

        if (startDate != null && endDate != null) {
            // Lọc danh sách đơn hàng theo thời gian
            ordersList = ordersRepository.findByOrderTimeBetween(startDate, endDate);
        } else {
            ordersList = ordersRepository.findAll(); // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Orders");

            // Tạo header cho các cột trong file Excel
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Mã Đơn");
            headerRow.createCell(1).setCellValue("Tên");
            headerRow.createCell(2).setCellValue("Địa Chỉ");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Số Điện Thoại");
            headerRow.createCell(5).setCellValue("Trạng Thái Đơn");
            headerRow.createCell(6).setCellValue("Số Lượng Sản Phẩm Trong Đơn");
            headerRow.createCell(7).setCellValue("Tổng Giá Sản Phẩm Trong Đơn");
            headerRow.createCell(8).setCellValue("Phương Thức Thanh Toán");
            headerRow.createCell(9).setCellValue("Thời Gian Đặt Hàng");

            // Thêm các cột khác tùy ý

            int rowNum = 1;
            for (Orders order : ordersList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrderID());
                row.createCell(1).setCellValue(order.getAccount().getFullname());
                row.createCell(2).setCellValue(order.getAccount().getAddress());
                row.createCell(3).setCellValue(order.getAccount().getUserName());
                row.createCell(4).setCellValue(order.getAccount().getPhone());
                row.createCell(5).setCellValue(order.getStatus().getName());
                row.createCell(6).setCellValue(order.getOrderitem().size());
                row.createCell(7).setCellValue(order.getTotalAmount());
                row.createCell(8).setCellValue(order.getPaymentMethod());
                row.createCell(9).setCellValue(order.getOrderTime().toString());
                // Điền thông tin các cột khác tùy ý
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // end

    // xuất excel theo id cho từng đơn hàng
    public byte[] exportOrderToExcel(Long orderId) {
        Orders order = ordersRepository.findById(orderId).orElse(null);

        if (order != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Orders");

                // Tạo header cho các cột trong file Excel
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Mã Đơn");
                headerRow.createCell(1).setCellValue("Tên");
                headerRow.createCell(2).setCellValue("Địa Chỉ");
                headerRow.createCell(3).setCellValue("Email");
                headerRow.createCell(4).setCellValue("Số Điện Thoại");
                headerRow.createCell(5).setCellValue("Trạng Thái Đơn");
                headerRow.createCell(6).setCellValue("Số Lượng Sản Phẩm Trong Đơn");
                headerRow.createCell(7).setCellValue("Tổng Giá Sản Phẩm Trong Đơn");
                headerRow.createCell(8).setCellValue("Phương Thức Thanh Toán");
                headerRow.createCell(9).setCellValue("Tên Sản Phẩm");
                headerRow.createCell(10).setCellValue("Giá Sản phẩm");
                headerRow.createCell(11).setCellValue("Số Lượng Sản Phẩm");
                headerRow.createCell(12).setCellValue("Kích Cỡ Sản Phẩm");
                headerRow.createCell(13).setCellValue("Thời Gian Đặt Hàng");

                // Thêm các cột khác tùy ý

                int rowNum = 1;

                // Lặp qua từng sản phẩm trong danh sách đơn hàng
                for (Order_Items orderItem : order.getOrderitem()) {
                    Row row = sheet.createRow(rowNum++);

                    row.createCell(0).setCellValue(order.getOrderID());
                    row.createCell(1).setCellValue(order.getAccount().getFullname());
                    row.createCell(2).setCellValue(order.getAccount().getAddress());
                    row.createCell(3).setCellValue(order.getAccount().getUserName());
                    row.createCell(4).setCellValue(order.getAccount().getPhone());
                    row.createCell(5).setCellValue(order.getStatus().getName());
                    row.createCell(6).setCellValue(order.getOrderitem().size());
                    row.createCell(7).setCellValue(order.getTotalAmount());
                    row.createCell(8).setCellValue(order.getPaymentMethod());

                    // Thiết lập thông tin của từng sản phẩm trong đơn hàng
                    row.createCell(9).setCellValue(orderItem.getProduct().getName());
                    row.createCell(10).setCellValue(orderItem.getPrice());
                    row.createCell(11).setCellValue(orderItem.getQuantity());
                    row.createCell(12).setCellValue(orderItem.getSize_Product());

                    row.createCell(13).setCellValue(order.getOrderTime().toString());
                    // Điền thông tin các cột khác tùy ý
                }

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                workbook.write(byteArrayOutputStream);

                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    // end
}
