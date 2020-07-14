package cn.bysonia.bootmybatisp.interceptor;

import cn.bysonia.bootmybatisp.common.annotation.SyslogAnnotation;
import cn.bysonia.bootmybatisp.model.Syslog;
import cn.bysonia.bootmybatisp.service.SyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SyslogInterceptor implements HandlerInterceptor {
    @Autowired
    private SyslogService syslogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("hello .....");
        // 将handler强转为HandlerMethod
        if (!(handler instanceof  HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        SyslogAnnotation syslogAnnotation = method.getAnnotation(SyslogAnnotation.class);
        if (syslogAnnotation == null){
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (syslogAnnotation != null){
            // 如果自定义注解不为空, 则取出配置值
            String value = syslogAnnotation.value();
            Syslog syslog = new Syslog();
            syslog.setMethod(value);
            addSyslog(syslog);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("bye .....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("get out of my eyes .....");
    }

    public void addSyslog(Syslog syslog){
        //get request
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> requestInfo = getRequestInfo(request);
        syslog.setRequesetUrl(requestInfo.get("RequestURI"));
        syslogService.insertSyslog(syslog);
    }


    private static Map<String,String> getRequestInfo(HttpServletRequest request) {
        Map<String, String> requestInfo = new HashMap<>();
        requestInfo.put("RemoteAddr", request.getRemoteAddr());
        requestInfo.put("RemoteHost", request.getRemoteHost());
        requestInfo.put("HttpMethod",request.getMethod());
        requestInfo.put("RequestURI",request.getRequestURI());

        return requestInfo;
    }

}