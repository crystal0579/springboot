package com.bysonia.sprisecuritydb.service.impl;

import com.bysonia.sprisecuritydb.model.Person;
import com.bysonia.sprisecuritydb.mapper.PersonMapper;
import com.bysonia.sprisecuritydb.service.PersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Sonia
 * @since 2020-03-06
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    /**
     * UserDetails是spring自己的用户对象，所以我们的思路是 先用username查询到自己的用户对象，然后再转成spring的userDetails
     * @param username 是用户在浏览器上自己输入的名字
     * @return 如果return null 在spring里就表示认证失败
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("usercode", username);
        List<Person> persons = baseMapper.selectByMap(paramMap);
        if (CollectionUtils.isEmpty(persons)){
            return null;
        }else{
            Person person = persons.get(0);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin"));//根据数据库的值来给  role表

            //这里传入的是数据库的用户名和密码，因为登录页面上的用户名和密码值spring已经知道，现在是要和底层校验
            //如果密码没有被加密过 ，请写上 {noop} 具体见 https://docs.spring.io/spring-security/site/docs/5.3.1.BUILD-SNAPSHOT/reference/html5/
            UserDetails userDetails = new User(person.getUsercode(), person.getPassword(),authorities );
            return userDetails;
        }

    }
}
