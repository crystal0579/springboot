package com.bysonia.sprisecutiy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableWebSecurity
public class SecurityConfigMemory extends WebSecurityConfigurerAdapter {

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //如果不写这些配置，那么首页都会被redirect到security内置的登录页去
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()//设置首页所有人都可以访问，功能页只有对应有权限的人才能被访问
                .antMatchers("/level1/**").hasRole("vip1")//vip1的人有权力访问， 于是下面有方法要定义哪些人有这些role
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
    /**
        //表示没有登录的话就跳到spring security的默认登录页面,除非自己controller 里覆盖掉 “/login” 的路径
        http.formLogin();//如果不写这句话，那么在没有登录的访问时会返回 access denied 的错误信息页面。 登录后而没有role的情况下是forbidden 的错误信息
       */

    //首页定制
        http.formLogin().loginPage("/toLogin")//这时候spring security 的默认首页 /login就失效了，变成了404
//                        .usernameParameter("username")//默认是 username
//                        .usernameParameter("password")//默认是 password
                        .loginProcessingUrl("/login");//而实际真正处理的页面还是spring security的内核/login方法(所以你的toLogin页面submit时的方法应该写"/login")，只是ui界面变成你的/toLogin


        //开启了注销功能,注销成功后并返回首页
//        http.csrf().disable();//关闭此功能， 因为 CSRF跨站点请求伪造(Cross—Site Request Forgery)，跟XSS攻击一样，存在巨大的危害性
        http.logout().logoutSuccessUrl("/");//要写在csrf().disable()下面，因为 html 一旦引用 security-thymleaf 标签 logout的话就会报错

        //cookie 记住密码功能, 默认保存2周
        http.rememberMe()
//            .rememberMeParameter("rememberMe")//自定义页面上的remeber me 的checkbox name
        ;
    }

    //认证  springboot 2.1.X 可以直接使用
    //重写关于认证管理的configure方法， 可以从数据库里管理也可以从内存里进行管理，可以从源码的此方法里获晓
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("---------------password authentication--------------------");

//        auth.jdbcAuthentication()//从数据库里进行认证 ,规范时的选择
        PasswordEncoder pwdEncode = new BCryptPasswordEncoder();//密码必须要经过编码，否则页面会报没有编码的错误
        auth.inMemoryAuthentication().passwordEncoder(pwdEncode)
//                .withUser("sonia").password("123456").roles("vip1", "vip2", "vip3")//密码必须要经过编码，否则页面会报没有编码的错误
                .withUser("sonia").password(pwdEncode.encode("123456")).roles("vip1", "vip2", "vip3")
                .and()
                .withUser("guest").password(pwdEncode.encode("123456")).roles("vip1")
                .and()
                .withUser("haha").password(pwdEncode.encode("123456")).roles("vip1","vip2");

//        User.UserBuilder  users = User.withDefaultPasswordEncoder();
    }
}
