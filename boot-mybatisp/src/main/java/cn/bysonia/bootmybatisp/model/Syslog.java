package cn.bysonia.bootmybatisp.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Syslog implements Serializable {
    private String remark;
    private String method;
    private String requestUrl;
    private String remoteAddr;
    private String queryString;
    private String errorInfo;
    private Boolean success;
    private String requestBody;
    private String responseBody;
    private String httpStatusCode;

}
