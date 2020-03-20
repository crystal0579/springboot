package cn.bysonia.bootmybatisp.controller;

import cn.bysonia.bootmybatisp.bean.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "user")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @Value(value="${roncoo.secret}")
    private String secret;

    @Value(value="${roncoo.number}")
    private int number;

    @Value(value="${roncoo.desc}")
    private String desc;

    @ApiOperation("用户控制类")//只能用在方法名上才奏效，不能用在类上
    @RequestMapping(value = "get")
    public Map<String, Object> get(@ApiParam @RequestParam String name){
        logger.debug("your name is {}",name);
        logger.trace("Entering application.");
        logger.error("Did it again!");
        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("hello", "world");
        map.put("secret", secret);
        map.put("number", number);
        map.put("desc", desc);
        return map;
    }

    //swagger 只要我们的接口返回值中存在实体类，它就会被扫描到swagger中，即便 User 没有 @ApiModel
    @ApiOperation("用户控制类")
    @RequestMapping(value = "find/{id}/{name}")
    public User get(@PathVariable int id, @PathVariable String name){
        User user = new User();
        user.setId(id);
        user.setAge(20);
        user.setDay(new Date());
        user.setName(name);
        return user;
    }
}
