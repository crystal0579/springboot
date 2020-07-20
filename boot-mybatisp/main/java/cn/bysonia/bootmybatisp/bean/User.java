package cn.bysonia.bootmybatisp.bean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@ToString
@Getter
@Setter
@Accessors( chain = true )
//@Api("注释")//与下等价
@ApiModel("用户实体类")//在swagger上面就会表达出来
public class User {
    @ApiModelProperty("用户ID")
    private int id;
    private String name;
    private Date day;
    private int age;

    public static void main(String[] args){
        User user = new User();
        user.setId(12).setName("sonia");
    }
}

