package cn.bysonia.bootmybatisp.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel("用户测试类")//因为Controller返回类型里没有它，所以swagger不会展现，即便出现 @ApiModel
public class UserTest {
    @ApiModelProperty("用户姓名")
    private String name;
    @ApiModelProperty("用户年龄")
    private int age;

}
