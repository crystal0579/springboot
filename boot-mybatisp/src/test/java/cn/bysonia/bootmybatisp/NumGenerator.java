package cn.bysonia.bootmybatisp;

import cn.bysonia.bootmybatisp.bean.User;
import cn.bysonia.bootmybatisp.service.Generator;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Random;

public class NumGenerator implements Generator<Integer> {
    int[] ages = {18,19,20};
    @Override
    public Integer text() {
        Random random = new Random();
        return ages[random.nextInt(3)];
    }

    public static void main(String[] args){
        NumGenerator numGenerator = new NumGenerator();
        System.out.println(numGenerator.text());

        User user = new User();
        user.setName("sonia");
        user.setId(20);
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
    }
}
