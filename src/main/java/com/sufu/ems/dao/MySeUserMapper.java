package com.sufu.ems.dao;


import com.sufu.ems.entity.SeRole;
import com.sufu.ems.entity.SeUser;
import com.sufu.ems.entity.SeUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sufu
 * @version 1.0.0
 * @date 2020/4/29 11:25
 * @description
 */
@Repository
public interface MySeUserMapper {
    SeUserRole getUserRolesString(Integer id);
    List<SeRole> getUserRolesByUserId(String[] id);
    SeUser loadUserByUserName(String username);
}
