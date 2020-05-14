package com.sufu.ems;

import com.sufu.ems.dao.MySeUserMapper;
import com.sufu.ems.dao.SeUserMapper;
import com.sufu.ems.entity.SeUser;
import com.sufu.ems.entity.TbExam;
import com.sufu.ems.entity.TbMajor;
import com.sufu.ems.entity.TbStudent;
import com.sufu.ems.service.TbExamService;
import com.sufu.ems.service.SeUserService;

import com.sufu.ems.service.TbMajorService;
import com.sufu.ems.service.TbStudentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@SpringBootTest(classes = EmsApplication.class)
@RunWith(SpringRunner.class)
//@Transactional
//@Rollback
class EmsApplicationTests {
    private Logger logger = LoggerFactory.getLogger(EmsApplicationTests.class);
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MySeUserMapper mySeUserMapper;
    @Autowired
    private SeUserMapper seUserMapper;
    @Autowired
    private SeUserService userService;
    @Autowired
    private TbStudentService tbStudentService;
    @Autowired
    private TbMajorService tbMajorService;
    @Autowired
    private TbExamService tbExamService;
    @Test
    void contextLoads() {
    }
    @Test
    void getPassword(){
        System.out.println(passwordEncoder.encode("123...qwe"));
    }
    @Test
    void testLoadUserByUsername(){
        userService.loadUserByUsername("苏伏");
    }
    @Test
    void testGetUser(){
        SeUser user = mySeUserMapper.loadUserByUserName("苏伏");
        System.out.println(user.getUsername());
    }
    @Test
    void testSeUserMapper(){
        Example example =new Example(SeUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", "苏伏");
        SeUser user = seUserMapper.selectOneByExample(example);
        System.out.println(user.getUsername());
    }
    @Test
    void testStudentServiceSelectAll(){
        List<TbStudent> list = tbStudentService.selectAll();
        for (TbStudent tbStudent : list) {
            System.out.println(tbStudent.toString());
        }
    }
    @Test
    void testSelectByStudentNumber(){
        String num = "11803990401";
        TbStudent tbStudent = tbStudentService.selectByStudentNumber(num);
        System.out.println(tbStudent.toString());

    }
    @Test
    void testInsert(){
        TbStudent tbStudent = new TbStudent();
        tbStudent.setStudentBirthday(new Date());
        tbStudent.setStudentClass("4");
        tbStudent.setStudentGrade("18");
        tbStudent.setStudentLevel("1");
        tbStudent.setStudentMajor("37");
        tbStudent.setStudentName("啦啦啦");
        tbStudent.setStudentSex("男");
        tbStudent.setStudentNumber("11803990403");
        tbStudentService.insert(tbStudent);
        List<TbStudent> list = tbStudentService.selectAll();
        for (TbStudent student : list) {
            System.out.println(student.toString());
        }
    }
    @Test
    void testInsertTbMajor(){
        TbMajor major = new TbMajor();
        major.setMajorNumber("0305");
        major.setMajorName("网络工程");
        major.setHeaderId("11803050401");
        major.setHeaderName("王五");
        major.setCollegeName("计算机科学与工程学院");
        tbMajorService.insert(major);
        List<TbMajor> list = tbMajorService.selectAll();
        for (TbMajor tbMajor : list) {
            System.out.println(tbMajor.toString());
        }
    }
    @Test
    void testSelectTbMajorByMajorNumber(){
        String majorNumber = "0307";
        System.out.println(tbMajorService.selectByMajorNumber(majorNumber));
    }
    @Test
    void testGetExams(){
        TbStudent tbStudent = new TbStudent();
        tbStudent.setStudentNumber("11803990401");
        tbStudent.setStudentMajor("0307");
        List<TbExam> tbExams = tbExamService.selectExams(tbStudent);
        for (TbExam tbExam : tbExams) {
            System.out.println(tbExam.toString());
        }
    }
}
