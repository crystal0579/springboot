package cn.bysonia.boot;

        import org.junit.jupiter.api.Test;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class BootApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedisAdd(){
        redisTemplate.opsForValue().set("name", "Sonia");
    }

    @Test
    public void testRedisGet(){
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("get the redis value is " + name);
    }
}
