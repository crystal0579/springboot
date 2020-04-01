package com.sonia.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonia.config.RsaKeyProperties;
import com.sonia.model.SysRole;
import com.sonia.model.SysUser;
import com.sonia.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private RsaKeyProperties rsaKeyProperties;

    public JwtLoginFilter(AuthenticationManager authenticationManager, RsaKeyProperties rsaKeyProperties) {
        this.authenticationManager = authenticationManager;
        this.rsaKeyProperties = rsaKeyProperties;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            SysUser user = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);//user 是从 request中过来的，并非数据库
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
//            this.setDetails(request, authRequest);
            return authenticationManager.authenticate(authRequest);//比对数据库中的数据
        } catch (IOException e) {

            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = response.getWriter();
                Map resultMap = new HashMap();
                resultMap.put("code", HttpServletResponse.SC_UNAUTHORIZED);
                resultMap.put("msg", "用户名或密码错误");
                out.write(new ObjectMapper().writeValueAsString(resultMap));

                out.flush();
                out.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException();
        }

    }

    //生成 JWT token 的地方
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SysUser user = new SysUser();
        user.setUsername(authResult.getName());
        user.setRoles((List<SysRole>) authResult.getAuthorities());
        String token = JwtUtils.generateTokenExpireInMinutes(user, rsaKeyProperties.getPrivateKey(), 24*60);
        response.addHeader("Authorization", "Bearer" + token);

//认证通过的返回信息
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        Map resultMap = new HashMap();
        resultMap.put("code", HttpServletResponse.SC_OK);
        resultMap.put("msg", "认证通过");
        out.write(new ObjectMapper().writeValueAsString(resultMap));

        out.flush();
        out.close();

    }
}
