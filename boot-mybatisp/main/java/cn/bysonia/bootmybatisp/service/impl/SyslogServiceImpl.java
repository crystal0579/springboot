package cn.bysonia.bootmybatisp.service.impl;

import cn.bysonia.bootmybatisp.model.Syslog;
import cn.bysonia.bootmybatisp.service.SyslogService;
import org.springframework.stereotype.Service;

@Service
public class SyslogServiceImpl implements SyslogService {
    @Override
    public void insertSyslog(Syslog syslog) {
        System.out.println(syslog);
    }
}
