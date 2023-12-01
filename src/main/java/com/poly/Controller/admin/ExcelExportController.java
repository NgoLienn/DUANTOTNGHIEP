package com.poly.Controller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.Service.ExcelExportService;

@Controller
@RequestMapping("/admin")
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;

    // xuất excel cho tất cả hóa đơn
    @GetMapping("/exportOrdersToExcel")
    public void exportOrdersToExcel(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            HttpServletResponse response) {
        byte[] excelContent;

        if (startDate != null && endDate != null) {
            excelContent = excelExportService.exportOrdersToExcel(startDate, endDate);
        } else {
            excelContent = excelExportService.exportAllOrdersToExcel();
        }

        if (excelContent != null) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Orders.xlsx");

            try {
                response.getOutputStream().write(excelContent);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // end

    // xuất excel theo id cho từng đơn hàng
    @GetMapping("/exportOrderToExcel/{orderId}")
    public void exportOrderToExcel(@PathVariable Long orderId, HttpServletResponse response) {
        byte[] excelContent = excelExportService.exportOrderToExcel(orderId);

        if (excelContent != null) {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Order_" + orderId + ".xlsx");

            try {
                response.getOutputStream().write(excelContent);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    // end

}
