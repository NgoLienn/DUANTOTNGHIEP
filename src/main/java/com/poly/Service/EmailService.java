package com.poly.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

 @Autowired
    private JavaMailSender emailSender; // Sử dụng JavaMailSender trong Spring

    public void sendCancellationEmail(String recipientEmail, String reason) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Thông báo hủy đơn hàng");
        message.setText("Đơn hàng của bạn đã bị hủy với lý do: " + reason);

        emailSender.send(message);
    }

}




