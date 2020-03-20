package cn.bysonia.bootmybatisp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/pass")//GetMapping等价于@RequestMapping的GET请求方式
    @ResponseBody//注意这里一定要加，否则会重定向到pass.html，可能不会符合拦截器里的拦截路径
    public String pass(){
        System.out.println(" this is in pass:");
        return "pass";
    }

    @RequestMapping("/fail")
    @ResponseBody
    public String fail(){
        return "fail";
    }

    @RequestMapping("/exe")
    @ResponseBody
    public String exe(){
        throw new RuntimeException("exe.....this is not found");
    }

}
