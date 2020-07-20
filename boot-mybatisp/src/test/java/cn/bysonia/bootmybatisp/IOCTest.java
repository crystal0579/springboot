package cn.bysonia.bootmybatisp;

import cn.bysonia.bootmybatisp.ioc.BeanA;
import cn.bysonia.bootmybatisp.ioc.BeanB;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

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


    public static String getOrderIdByUUId() {
        int first = new Random(10).nextInt(8) + 1;
        System.out.println(first);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return first + String.format("%015d", hashCodeV);
    }
    public static void main(String[] args) {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyMMddHHmmssSS");
        System.out.println("时间戳："+sdfTime.format(new Date()));
        Random r = new Random();
        System.out.println("---------" + r.nextInt(9));
        String orderingID= getOrderIdByUUId();
        System.out.println(orderingID);
    }
}
