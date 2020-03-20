package cn.bysonia.bootmybatisp;

import cn.bysonia.bootmybatisp.bean.User;
import cn.bysonia.bootmybatisp.controller.UserController;
import cn.bysonia.bootmybatisp.model.Person;
import cn.bysonia.bootmybatisp.service.PersonService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BootMybatispApplicationTests {
    @Autowired
    PersonService personService;

    /**
     * 如何用test 类来模拟request发起， 来测试 controller
     * --------------------start
     */
    private MockMvc mockMvc;

    private RestTemplateBuilder restTemplateBuilder;

    //    @Before//不想所有的test都before
    public void before(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//        this.restTemplateBuilder = new RestTemplateBuilder();
    }

    @Test
    void contextLoads() throws Exception {
        before();//不想所有的test都before
        RequestBuilder req = get("/user/get?name=20");
        mockMvc.perform(req).andExpect(status().isOk()).andExpect(content().string("{\"name\":\"20\",\"hello\":\"world\"}"));
        req = get("/user//find/1/sonia");
        mockMvc.perform(req).andExpect(status().isOk()).andExpect(content().string("{\"id\":1,\"name\":\"sonia\",\"day\":1582967555681,\"age\":20}"));

//        restTemplateBuilder.build().getForObject("/user/...", User.class);//postForObject

    }

    /**
     * ----------------------end
     */
    @Test
    public void testGetPerson(){
        Person person = personService.selectById(1);
        System.out.println(person);
    }

    @Test
    public void testUpdatePerson(){
        Person person = new Person();
        person.setId(1);
        person.setAge(17);
        personService.updateById(person);
    }

    @Test
    public void testPage(){
        Page<Person> page = new Page<>();
        page.setCurrent(0);
        page.setSize(3);
        Page<Person> personPage = personService.selectPage(page);
        System.out.println(JSON.toJSONString(personPage));
    }





}
