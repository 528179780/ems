package com.sufu.ems.service;

import com.sufu.ems.dao.MySeUserMapper;
import com.sufu.ems.dao.SeUserMapper;
import com.sufu.ems.entity.SeRole;
import com.sufu.ems.entity.SeUser;
import com.sufu.ems.entity.SeUserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/29 10:57
 * @description
 */
@Service
public class SeUserService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(SeUserService.class);
    @Autowired
    private SeUserMapper userMapper;
    @Autowired
    MySeUserMapper mySeUserMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Example example = new Example(SeUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", s);
        SeUser user = userMapper.selectOneByExample(example);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在");
        }
        SeUserRole seUserRole = mySeUserMapper.getUserRolesString(1);
        String rolesString = seUserRole.getRid();
        String[] string = rolesString.split(",");
        user.setRoles(mySeUserMapper.getUserRolesByUserId(string));
        logger.info("根据用户名获取用户{}成功",user.getUsername());
        return user;
    }
}
