package cn.bysonia.bootmybatisp.config;

import cn.bysonia.bootmybatisp.interceptor.HelloInterceptor;
import cn.bysonia.bootmybatisp.interceptor.SyslogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc//之所以注释它的理由是因为 application.properties里的 spring.jackson.date-format=yyyy-MM-dd HH:mm:ss  失效了
public class SpringbootConfig implements WebMvcConfigurer {

    @Bean
    public HelloInterceptor getHelloInterceptor(){
        return new HelloInterceptor();
    }

    @Bean
    public SyslogInterceptor getSyslogInterceptor() {
        return new SyslogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(getHelloInterceptor())
                            .addPathPatterns("/test/pass")//拦截路径
                            .excludePathPatterns("/test/fail");//放行路径，其实这里没有什么太大意义
        interceptorRegistry.addInterceptor(getSyslogInterceptor())
                .addPathPatterns("/**");
        /**
         * 这种情况用得会比较多
         * interceptorRegistry.addPathPatterns("/**")
         *         .excludePathPatterns("/index");
         */

    }
}
