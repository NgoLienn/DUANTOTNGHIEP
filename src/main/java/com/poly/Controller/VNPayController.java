package com.poly.Controller;

import com.poly.Config.Config;
import com.poly.Entity.Carts;
import com.poly.Entity.Payment;
import com.poly.Reponsitory.PaymentRepository;
import com.poly.Reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/payment")
public class VNPayController {

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping("/vnp")
    public String payment(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_CurrCode="VND";
        String vnp_OrderInfo = req.getParameter("course");
        String orderType = "1";
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_IpAddr = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;
//      long amount = Integer.parseInt(req.getParameter("vnp_Amount"))*100;
        long amount =10000;
//      int product = Integer.parseInt(req.getParameter("product"));
//       Course course1 =courseDAO.findById(course);
        String username= req.getRemoteUser();
        System.out.println(amount);

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount*100));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", orderType);
        String bank_code = req.getParameter("bankcode");
        if (bank_code != null && !bank_code.isEmpty()) vnp_Params.put("vnp_BankCode", bank_code);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        String locate = req.getParameter("language");
        if (locate != null && !locate.isEmpty()) vnp_Params.put("vnp_Locale", locate);
        else vnp_Params.put("vnp_Locale", "vn");

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());

        Payment paymentEntity=new Payment();
        paymentEntity.setAmount(amount/100);
        paymentEntity.setStatus("NO");
        paymentEntity.setTxnref(vnp_TxnRef);
        paymentEntity.setCreatedate(vnp_CreateDate);
        paymentEntity.setBankcode(bank_code);
        paymentEntity.setCurrcode(vnp_CurrCode);
//        paymentEntity.setCoursePayment(course1);
        paymentEntity.setUsername(username);
        paymentRepository.save(paymentEntity);


        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String hr =Config.vnp_HashSecret;
        System.out.println(hr);
        String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
        System.out.println(vnp_SecureHash);
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
        resp.setStatus(HttpServletResponse.SC_FOUND);
        resp.setHeader("Location", paymentUrl);
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
        return "redirect:" + paymentUrl;
    }



}


