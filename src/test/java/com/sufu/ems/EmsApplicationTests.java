package com.sufu.ems;

import com.sufu.ems.dao.MySeUserMapper;
import com.sufu.ems.dao.SeUserMapper;
import com.sufu.ems.entity.SeRole;
import com.sufu.ems.entity.SeUser;
import com.sufu.ems.service.SeUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


@SpringBootTest(classes = EmsApplication.class)
@RunWith(SpringRunner.class)
class EmsApplicationTests {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MySeUserMapper mySeUserMapper;
    @Autowired
    private SeUserMapper seUserMapper;
    @Autowired
    SeUserService userService;
    @Test
    void contextLoads() {
    }
    @Test
    void getPassword(){
        System.out.println(passwordEncoder.encode("123...qwe"));
    }
    @Test
    void testLoadUserByUsername(){
        userService.loadUserByUsername("sufu");
    }
    @Test
    void testGetUser(){
        SeUser user = mySeUserMapper.loadUserByUserName("sufu");
        System.out.println(user.getUsername());
    }
    @Test
    void testSeUserMapper(){
        Example example =new Example(SeUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", "sufu");
        SeUser user = seUserMapper.selectOneByExample(example);
        System.out.println(user.getUsername());
    }
}
