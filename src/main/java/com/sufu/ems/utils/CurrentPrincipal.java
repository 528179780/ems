package com.sufu.ems.utils;

import com.sufu.ems.entity.SeUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author sufu
 * @date 2020/6/8 上午11:12
 * @description 获取当前登陆用户信息的类
 **/

public class CurrentPrincipal {
    /**
     * 获取当前用户
     * @return
     */
    public static Authentication getCurrentUserAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     * @return
     */
    public static SeUser getCurrentPrincipal(){
        return (SeUser) getCurrentUserAuthentication().getPrincipal();
    }
}
