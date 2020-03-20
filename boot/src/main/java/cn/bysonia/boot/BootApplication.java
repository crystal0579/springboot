package cn.bysonia.boot;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
//@MapperScan("cn.bysonia.boot.dao")
@EnableScheduling
//public class BootApplication extends SpringBootServletInitializer { //部署模式
public class BootApplication { //普通模式
    @Autowired
    RedisTemplate redisTemplate;

////部署模式中 重写 SpringBootServletInitializer的 configure方法
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(BootApplication.class);
//    }

    public static void main(String[] args) {
        //这是正常的启动方式
        SpringApplication.run(BootApplication.class, args);
//        //这是外部部署的方式
//        new SpringApplicationBuilder(BootApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }


}
