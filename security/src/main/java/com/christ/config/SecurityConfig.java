package com.christ.config;

import com.alibaba.fastjson.JSON;
import com.christ.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 史偕成
 * @date 2023/08/04 11:01
 **/
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            try {
                auth.anyRequest().authenticated()
                        .and()
                        // 添加JWT token认证
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        // 添加数据源读取用户名密码
                        .userDetailsService(userDetailsService)
                        // Session会话管理  这个策略是无状态会话 对应JWT（Json web token）
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
//                        .and()
//                        .antMatcher("/login").anonymous()
                        .formLogin()
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        // 跳转静态页面地址
                        .failureUrl("/login.html")
                        // 跳转接口url
                        .failureForwardUrl("/loginFail")
                        // 设置登陆用户名参数
                        .usernameParameter("user")
                        // 设置密码参数
                        .passwordParameter("pw")
                        .permitAll()
                        // 成功后回调执行器
                        .successHandler((request, response, authentication) -> {
                            Map<String, Object> result = new HashMap<String, Object>();
                            result.put("code", 0);
                            result.put("msg", "登陆成功");
                            result.put("data", authentication);
                            writeResp(result, response);
                        })
                        // 失败后回调执行器
                        .failureHandler((request, response, exception) -> {
                            Map<String, Object> result = new HashMap<String, Object>();
                            result.put("code", 0);
                            result.put("msg", exception.getMessage());
                            result.put("data", null);
                            writeResp(result, response);
                        })
                        .and()
                        .csrf().disable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
        return http.build();
    }


    private void writeResp(Map<String, Object> result, HttpServletResponse response) {
        response.setContentType("application/json;charset=utf-8");
        try {
            response.getWriter().println(JSON.toJSONString(result));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
