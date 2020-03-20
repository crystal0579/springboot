package cn.bysonia.bootmybatisp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket1(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hahahaha1");//就是上面得搜索框
    }

    @Bean
    public Docket docket2(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hahahaha2");//就是上面得搜索框
    }

    @Bean
    public Docket docket3(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hahahaha3");//就是上面得搜索框
    }

    @Bean
    public Docket docket(Environment environment){
        //设定要显示swagger的环境
        Profiles profiles = Profiles.of("dev", "test");
        //判断是否处在自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("hahahaha")//就是上面得搜索框
                .enable(flag)  //整体关闭
                .select()
                //RequestHandlerSelectors 配置要扫描的接口方式
                //.basePackage:指定扫描的包
                //.any 全部都扫描
                //.none 。。都不扫描
                //.withClassAnnotation 扫描类上的注解
                //.withMethodAnnotation 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("cn.bysonia.bootmybatisp.controller"))
                //过滤什么路径(只出什么路径)
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Sonia", "https://www.baidu.com", "wengjuan025@163.com");
       return new ApiInfo("Sonia's Swagger",
                        "always beautiful",
                        "v1.0",
                        "http://www.baidu.com",
//                         ApiInfo.DEFAULT_CONTACT,
                        contact,
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                          new ArrayList());
    }
}
