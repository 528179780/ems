package com.sufu.ems.configuration;

import com.sufu.ems.service.SeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/24 21:56
 * @description
 */
@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    SeUserService seUserService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * @Author sufu
     * @Date 2020/5/5 10:50
     * @param
     * @return org.springframework.security.access.hierarchicalroles.RoleHierarchy
     * @Description 设置权限继承 admin也具有user权限
     **/
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }
    /**
     * @author sufu
     * @date 2020/5/5 10:51
     * @param auth 配置器
     * @return void
     * @description 配置验证用户的service
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(seUserService);
    }

    /**
     * @author sufu
     * @date 2020/5/5 10:56
     * @param http
     * @return void
     * @description 配置权限拦截链
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }
}
