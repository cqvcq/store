package com.cq.dao;

import com.cq.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {
    @Select("select * from sys_permission")
    List<Permission> findAll();

    @Select("select * from sys_permission where pid = 0")
    List<Permission> saveUI();

    @Insert("insert into sys_permission values(null,#{permissionName},#{url},#{pid})")
    void save(Permission permission);

    @Select("select p.* from sys_permission p,sys_role_permission rp where rp.roleId = #{rid} and rp.permissionId=p.id")
    List<Permission> findById(Integer rid);
}
