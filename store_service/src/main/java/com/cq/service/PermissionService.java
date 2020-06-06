package com.cq.service;

import com.cq.pojo.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    List<Permission> saveUI();

    void save(Permission permission);
}
