package com.poly.DTO;


import com.poly.Config.Config;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PymentRequestDTO {
    private String vnp_RequestId;
    private String vnp_Version;
    private String vnp_Command;
    private String vnp_TmnCode;
    private String vnp_TxnRef;
    private String vnp_TransactionType;
    private String vnp_Amount;
    private String vnp_TransactionNo;
    private String vnp_TransactionDate;
    private String vnp_CreateBy;
    private String vnp_createDate;
    private String vnp_IpAddr;
    private String vnp_OrderInfo;
    private String vnp_SecureHash;
    public String generateUrl() {
        StringBuilder query = new StringBuilder();
        try {
            query.append("vnp_RequestId").append("=").append(URLEncoder.encode(getVnp_RequestId(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_Version").append("=").append(URLEncoder.encode(getVnp_Version(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_Command").append("=").append(URLEncoder.encode(getVnp_Command(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_TmnCode").append("=").append(URLEncoder.encode(getVnp_TmnCode(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_TxnRef").append("=").append(URLEncoder.encode(getVnp_TxnRef(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_Amount").append("=").append(URLEncoder.encode(getVnp_Amount(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_TransactionNo").append("=").append(URLEncoder.encode(getVnp_TransactionNo(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_TransactionDate").append("=").append(URLEncoder.encode(getVnp_TransactionDate(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_createDate").append("=").append(URLEncoder.encode(getVnp_createDate(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_IpAddr").append("=").append(URLEncoder.encode(getVnp_IpAddr(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_OrderInfo").append("=").append(URLEncoder.encode(getVnp_OrderInfo(), StandardCharsets.US_ASCII.toString())).append("&")
                    .append("vnp_SecureHash").append("=").append(URLEncoder.encode(getVnp_SecureHash(), StandardCharsets.US_ASCII.toString()));
        } catch (Exception exception) {

        }
        System.out.println(query);
        return query.toString();
    }
    public String generateHash() {
        StringBuilder query = new StringBuilder();
        try {
            query.append("vnp_RequestId").append("=").append(URLEncoder.encode(getVnp_RequestId(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_Version").append("=").append(URLEncoder.encode(getVnp_Version(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_Command").append("=").append(URLEncoder.encode(getVnp_Command(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_TmnCode").append("=").append(URLEncoder.encode(getVnp_TmnCode(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_TxnRef").append("=").append(URLEncoder.encode(getVnp_TxnRef(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_Amount").append("=").append(URLEncoder.encode(getVnp_Amount(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_TransactionNo").append("=").append(URLEncoder.encode(getVnp_TransactionNo(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_TransactionDate").append("=").append(URLEncoder.encode(getVnp_TransactionDate(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_createDate").append("=").append(URLEncoder.encode(getVnp_createDate(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_IpAddr").append("=").append(URLEncoder.encode(getVnp_IpAddr(), StandardCharsets.US_ASCII.toString())).append("|")
                    .append("vnp_OrderInfo").append("=").append(URLEncoder.encode(getVnp_OrderInfo(), StandardCharsets.US_ASCII.toString()));
        } catch (Exception exception) {

        }
        vnp_SecureHash= Config.hmacSHA512(Config.vnp_HashSecret,query.toString());
        return vnp_SecureHash;
    }

}
