package cn.bysonia.bootmybatisp.service;

import cn.bysonia.bootmybatisp.model.Syslog;
import com.baomidou.mybatisplus.service.IService;

public interface SyslogService {
    public void insertSyslog(Syslog syslog);
}
