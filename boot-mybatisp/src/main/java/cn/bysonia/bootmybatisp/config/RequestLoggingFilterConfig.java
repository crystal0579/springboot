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

@Configuration
@WebFilter(urlPatterns = { "*" })
@Order(99)
public class RequestLoggingFilterConfig extends AbstractRequestLoggingFilter {
    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        setIncludePayload(true);
        setMaxPayloadLength(10000);
        setIncludeQueryString(true);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        super.doFilterInternal(request, responseWrapper, filterChain);
    }

    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {

    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {

    }
}
