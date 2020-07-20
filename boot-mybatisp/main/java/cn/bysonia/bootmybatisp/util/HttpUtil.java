package cn.bysonia.bootmybatisp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;

@Slf4j
public class HttpUtil {
    public static String getRequestBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.warn("处理异常",e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.warn("处理异常",e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.warn("处理异常",e);
                }
            }
        }
        return sb.toString();
    }

    public static String getResponseBody(HttpServletResponse response) {
        String result = "";
        try {
            ResponseWrapper wrapper = new ResponseWrapper( response);
            result = wrapper.getResponseData(response.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String  returnJson(HttpServletResponse response) throws Exception{
        String json = "";
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            log.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
        return json;
    }
}
