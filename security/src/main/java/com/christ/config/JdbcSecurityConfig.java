package com.christ.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author 史偕成
 * @date 2023/08/04 16:29
 **/
@RequiredArgsConstructor
@Configuration
//@ConditionalOnProperty(prefix = "christ", name = "mode", havingValue = "jdbc")
public class JdbcSecurityConfig {


    private final DataSource dataSource;

    /**
     * JDBC 写入用户信息
     *
     * @return
     */
//    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager() {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        if (!jdbcUserDetailsManager.userExists("lglbc-jdbc")) {
            jdbcUserDetailsManager.createUser(User.withUsername("lglbc-jdbc").username("lglbc-jdbc").password("{noop}lglbc-jdbc").roles("admin").build());
        }
        if (!jdbcUserDetailsManager.userExists("lglbc-jdbc2")) {
            jdbcUserDetailsManager.createUser(User.withUsername("lglbc-jdbc2").username("lglbc-jdbc2").password("{noop}lglbc-jdbc2").roles("admin").build());
        }
        return jdbcUserDetailsManager;
    }
}
