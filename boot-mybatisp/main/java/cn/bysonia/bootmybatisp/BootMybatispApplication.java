package cn.bysonia.bootmybatisp;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@MapperScan("cn.bysonia.bootmybatisp.mapper")
@EnableTransactionManagement
@ServletComponentScan
public class BootMybatispApplication{

    public static void main(String[] args) {
        SpringApplication.run(BootMybatispApplication.class, args);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
    @Bean
    public FilterRegistrationBean  loggingFilterRegistration() {

        AbstractRequestLoggingFilter loggingFilter =  new AbstractRequestLoggingFilter() {
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
            protected void beforeRequest(HttpServletRequest request, String message) {

            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {

            }
        };
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(loggingFilter);
        registration.addUrlPatterns("/user");
        registration.setName("loggingFilter");
        registration.setOrder(99);
        return registration;
    }
*/
//    mybatis-plus 还能开启性能监控
    @Bean
    @Profile({"dev", "test"})//设置dev,test环境开启
    public PerformanceInterceptor performanceInterceptor(){
        return new PerformanceInterceptor();
    }
}
