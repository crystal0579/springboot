package cn.bysonia.bootmybatisp.interceptor;

import cn.bysonia.bootmybatisp.common.annotation.SyslogAnnotation;
import cn.bysonia.bootmybatisp.model.Syslog;
import cn.bysonia.bootmybatisp.service.SyslogService;
import cn.bysonia.bootmybatisp.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

@Component
public class SyslogInterceptor implements HandlerInterceptor {
    @Autowired
    private SyslogService syslogService;

//    Logger LOGGER = LoggerFactory.getLogger(SyslogInterceptor.class);

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--------------------preHandle----------------------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        startTimeThreadLocal.set(startTime);

        // 将handler强转为HandlerMethod
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        SyslogAnnotation syslogAnnotation = method.getAnnotation(SyslogAnnotation.class);
        if (syslogAnnotation == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (syslogAnnotation != null) {
            // 如果自定义注解不为空, 则取出配置值
            Syslog syslog = new Syslog();
            addSyslog(request, syslog, syslogAnnotation, response);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("-----------------------------postHandle-----------------------------");
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (handler instanceof HandlerMethod) {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("whole CostTime  : ").append(executeTime).append("ms").append("\n");
//            System.out.println("----get ResponseBody---:" + HttpUtil.returnJson(response));
            System.out.println(sb.toString());
        }
    }

    //异常处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("--------------------------afterCompletion--------------------");
        // 打印JVM信息。
        // 将handler强转为HandlerMethod
        if (!(handler instanceof HandlerMethod)) {
            return;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        SyslogAnnotation syslogAnnotation = method.getAnnotation(SyslogAnnotation.class);
        if (syslogAnnotation == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return;
        }
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis();    //2、结束时间

        Syslog syslog = new Syslog();
        //如果controller报错，则记录异常错误
        if (ex != null) {
//                LOGGER.debug("Controller异常: " + getStackTraceAsString(ex));
            System.out.println("Controller异常: " + getStackTraceAsString(ex));
//            syslog.setErrorInfo(getStackTraceAsString(ex));
            syslog.setErrorInfo(ex.getMessage() + ":" + ex.getCause());
            syslog.setSuccess(false);
        }
        syslog.setSuccess(true);
        StringBuilder sb = new StringBuilder();
        sb.append("计时结束：" + new SimpleDateFormat("hh:mm:ss.SSS").format(endTime) + " 耗时：")
                .append(endTime - beginTime + " URI:")
                .append(request.getRequestURI() + " 最大内存: ")
                .append(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m  已分配内存: ")
                .append(Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m  已分配内存中的剩余空间: ")
                .append(Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m  最大可用内存: ")
                .append((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024 + "m");
        System.out.println(sb.toString());
        addSyslog(request, syslog, syslogAnnotation, response);
        startTimeThreadLocal.remove();


    }

    public void addSyslog(HttpServletRequest request, Syslog syslog, SyslogAnnotation syslogAnnotation, HttpServletResponse response) {
        System.out.println("------------------------------add syslog------------------------------------");
        //get request
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String value = syslogAnnotation.value();
        syslog.setMethod(value);
        syslog.setRequestUrl(request.getRequestURI());
        syslog.setRemoteAddr(request.getRemoteAddr());
        syslog.setQueryString(request.getQueryString());

        syslog.setRequestBody(getRequestPayload(request));
//        syslog.setResponseBody(HttpUtil.getResponseBody(response));
        syslog.setResponseBody(getResponsePayload(response));
        syslog.setHttpStatusCode(response.getStatus() + "");
        syslogService.insertSyslog(syslog);
    }


    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private static String getRequestPayload(HttpServletRequest request){
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if(wrapper != null){
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0){
                try {
                    return new String(buf,0,buf.length,wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String getResponsePayload(HttpServletResponse response){
        ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
//        HttpServletResponse rawResponse = (HttpServletResponse) responseWrapper.getResponse();
//        int statusCode = responseWrapper.getStatus();
//
//        if (rawResponse.isCommitted()) {
//            try {
//                responseWrapper.copyBodyToResponse();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        if(responseWrapper != null){
            byte[] buf = responseWrapper.getContentAsByteArray();
            if (buf.length > 0){
                try {
                    return new String(buf,0,buf.length,responseWrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }




}