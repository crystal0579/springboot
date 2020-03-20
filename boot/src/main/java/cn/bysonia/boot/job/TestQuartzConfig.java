package cn.bysonia.boot.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Name;

@Configuration
public class TestQuartzConfig {

    @Bean
    public JobDetail testQuartzDetail(){
        return JobBuilder.newJob(TestQuartZ.class)
                .withIdentity("TestQuartz")
                .storeDurably()
                .requestRecovery(true)
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","bySonia");

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever();

        return TriggerBuilder.newTrigger().forJob(testQuartzDetail())
                .withIdentity("testQuartz")
                .withSchedule(scheduleBuilder)
                .usingJobData(jobDataMap)
                .build();
    }
}
