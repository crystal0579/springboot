package cn.bysonia.bootmybatisp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecurityController {

    @RequestMapping(value = "level/1")
    public String level1(@RequestBody JsonNode jsonNode){

        return "level1";
    }

    @RequestMapping(value = "level2/1")
    public String level2(@RequestBody JsonNode jsonNode){

        return "level2";
    }

//    @RequestMapping(value = "/login")
//    public String level3(@RequestBody JsonNode jsonNode){
//
//        return "login";
//    }


}
