package cn.bysonia.bootmybatisp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LoggerTest {
    @Test
    public void test(){
        String name = "aaaaa";
        log.debug("bug ....... :");
        log.info("info ......."+name);
        log.info("aaa: {}", name);
        log.error("error .....");

    }
}
