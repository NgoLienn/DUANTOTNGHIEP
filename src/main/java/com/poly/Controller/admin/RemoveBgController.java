package com.poly.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.File;
import java.io.IOException;

@Controller
public class RemoveBgController {

        @RequestMapping("/url")
        public String removeBackground() throws IOException {
                Response response = Request.Post("https://api.remove.bg/v1.0/removebg")
                                .addHeader("X-Api-Key", "GN2ULwMosewpuewn4vJe4GPg")
                                .body(
                                                MultipartEntityBuilder.create()
                                                                .addTextBody("image_url",
                                                                                "https://bizweb.dktcdn.net/100/339/225/files/thuc-an-nhanh.jpg?v=1627638748869")
                                                                .addTextBody("size", "auto")
                                                                .build())
                                .execute();
                response.saveContent(new File("no-bg.png"));

                return "redirect:/";
        }

}
