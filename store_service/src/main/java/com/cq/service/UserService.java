package com.cq.service;

import com.cq.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<SysUser> findAll();

    void save(SysUser sysUser);

    boolean isUniqueUsername(String username);

    SysUser findById(Integer id);

    void addRoleToUser(Integer userId, Integer[] ids);

    void delete(Integer id);

    Integer findIdByUsername(String username);

    void register(SysUser sysUser);
}
