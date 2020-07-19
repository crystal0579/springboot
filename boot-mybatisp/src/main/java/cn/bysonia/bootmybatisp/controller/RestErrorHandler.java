package cn.bysonia.bootmybatisp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class RestErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
//        if (!response.getStatusCode().equals(HttpStatus.ACCEPTED)) {
//           System.out.println("have error");
//        }
        if(response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR){
            //handle 5xx errors
            System.out.println(response.getRawStatusCode());
            System.out.println(response.getStatusCode());
        }else if(response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR){
            System.out.println(response.getRawStatusCode());
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            HttpHeaders headers = response.getHeaders();

            System.out.println(headers.get("Content-Type"));
            System.out.println(headers.get("Server"));

        }
    }
}