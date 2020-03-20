package cn.bysonia.boot.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

    @Scheduled(cron = "0/2 * * * * *")//every 2 seconds to exec
    public void job(){
        System.out.println("ding shi ren wu");
    }
}
