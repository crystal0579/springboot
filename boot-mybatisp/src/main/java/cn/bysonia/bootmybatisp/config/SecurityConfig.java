package cn.bysonia.bootmybatisp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页是所有人都可以访问的，但是功能也只对应有权限的人才能访问
        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/user/**").permitAll()
                                .antMatchers("/level1/**").hasRole("vip1")
                                .antMatchers("/level2/**").hasRole("vip2")
                                .antMatchers("/level3/**").hasRole("vip3")
                                .and()
                                .csrf().disable();

        //没有权限会默认到登录页面
        //内置/login的页面
        http.formLogin();
    }

    /**
     * 去开启某个用户的认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //用内存去开启认证, 这些数据正常应该从数据库读取
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("sonia").password(passwordEncoder.encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(passwordEncoder.encode("654321")).roles("vip1","vip2","vip3");
    }
}
