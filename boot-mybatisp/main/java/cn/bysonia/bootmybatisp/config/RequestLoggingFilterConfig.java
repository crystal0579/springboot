package cn.bysonia.bootmybatisp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Configuration
@WebFilter(urlPatterns = { "/user/*" }, filterName = "requestLoggingFilterConfig")
@Order(99)
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {
    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        setIncludePayload(true);//request 的重置,里面实际上就是使用ContentCachingRequestWrapper
        setMaxPayloadLength(10000);
        setIncludeQueryString(true);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        super.doFilterInternal(request, responseWrapper, filterChain);
        responseWrapper.copyBodyToResponse();//这句话尤为重要，虽然没有这句话，日志里也能取到responseBody，但是，返回流的内容被消费掉了（在responseWrapper 生成时），不写的话客户将读不到本身流的responseBody
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {

    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {

    }
}
