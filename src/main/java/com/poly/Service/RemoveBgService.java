package com.poly.Service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RemoveBgService {
    @Value("${removebg.apiKey}")
    private String apiKey;

    @Value("${removebg.baseUrl}")
    private String baseUrl;

    public byte[] removeBackground(byte[] imageData) throws IOException {
        String apiUrl = baseUrl + "/removebg";
        String apiKeyHeader = "X-Api-Key";

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);

        httpPost.setHeader(apiKeyHeader, apiKey);

        String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
        String jsonRequest = String.format("{\"image\":\"%s\"}", base64Image);

        httpPost.setEntity(new StringEntity(jsonRequest, ContentType.APPLICATION_JSON));

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return entity.getContent().readAllBytes();
        } else {
            throw new IOException("Failed to remove background. Empty response.");
        }
    }
}
