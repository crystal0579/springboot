package cn.bysonia.bootmybatisp;

import cn.bysonia.bootmybatisp.ioc.BeanA;
import cn.bysonia.bootmybatisp.ioc.BeanB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IOCTest {

    @Autowired
    BeanB beanB;

    @Test
    public void testIoc(){
        BeanA a1 = beanB.getBeanA();
        BeanA a2 = beanB.getBeanA();
        System.out.println(a1 == a2);
    }

}
