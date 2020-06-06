package com.cq.service.impl;

import com.cq.dao.RoleDao;
import com.cq.pojo.Role;
import com.cq.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById2(id);
    }

    @Override
    public void addPermissionToRole(Integer roleId, Integer[] ids) {
        roleDao.delete(roleId);
        if(ids!=null){
            for (Integer id : ids) {
                roleDao.addPermissionToRole(roleId,id);
            }
        }
    }
}
