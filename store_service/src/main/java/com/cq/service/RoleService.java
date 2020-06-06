package com.cq.service;

import com.cq.pojo.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(Integer id);

    void addPermissionToRole(Integer roleId, Integer[] ids);
}
