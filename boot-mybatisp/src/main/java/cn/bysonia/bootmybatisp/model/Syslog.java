package cn.bysonia.bootmybatisp.model;

import java.io.Serializable;

public class Syslog implements Serializable {
    private String remark;
    private String method;
    private String requesetUrl;

    public String getRequesetUrl() {
        return requesetUrl;
    }

    public void setRequesetUrl(String requesetUrl) {
        this.requesetUrl = requesetUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Syslog{" +
                "remark='" + remark + '\'' +
                ", method='" + method + '\'' +
                ", requesetUrl='" + requesetUrl + '\'' +
                '}';
    }
}
