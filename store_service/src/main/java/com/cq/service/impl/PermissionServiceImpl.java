package com.cq.service.impl;

import com.cq.dao.PermissionDao;
import com.cq.pojo.Permission;
import com.cq.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public List<Permission> saveUI() {
        return permissionDao.saveUI();
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
