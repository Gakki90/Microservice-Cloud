package com.atguigu.springcloud.cfgbeans;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class TokenInterceptor implements ClientHttpRequestInterceptor {
    private String token;

    public TokenInterceptor(String token) {
        this.token=token;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        System.err.println("进入RestTemplate拦截器");
        HttpHeaders headers = request.getHeaders();

        headers.add("Authorization", token);
        return execution.execute(request, body);
    }
}
