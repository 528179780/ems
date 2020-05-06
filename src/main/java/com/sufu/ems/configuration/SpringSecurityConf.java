package com.sufu.ems.configuration;

import com.sufu.ems.service.SeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/24 21:56
 * @description
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    SeUserService seUserService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /**
     * @author sufud
     * @date 2020/5/5 10:50
     * @param
     * @return org.springframework.security.access.hierarchicalroles.RoleHierarchy
     * @description 设置权限继承 admin也具有user权限
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
                .antMatchers("/static/**","/assets/**").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/student/**").hasRole("student")
                .antMatchers("/db/**").hasRole("db")
                .antMatchers("/teacher/**").hasRole("teacher")
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)//清除身份认证信息
                .invalidateHttpSession(true)//使session失效
                .and()
                .csrf().disable();
        http.rememberMe();
    }
}
