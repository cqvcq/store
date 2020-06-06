package com.cq.dao;

import com.cq.pojo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleDao {


    @Select("select * from sys_role")
    List<Role> findAll();

    @Insert("insert into sys_role values(null,#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select r.* from sys_user_role ur,sys_role r where ur.userId = #{uid} and ur.roleId=r.id")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "permissionList",column = "id",javaType = List.class,
            many = @Many(select = "com.cq.dao.PermissionDao.findById",fetchType = FetchType.LAZY))
    })
    List<Role> findById(Integer uid);

    @Select("select * from sys_role where id = #{id}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "permissionList",column = "id",javaType = List.class,
                    many = @Many(select = "com.cq.dao.PermissionDao.findById",fetchType = FetchType.LAZY))
    })
    Role findById2(Integer id);

    @Delete("delete from sys_role_permission where roleId = #{roleId}")
    void delete(Integer roleId);

    @Insert("insert into sys_role_permission values (#{param2},#{param1})")
    void addPermissionToRole(Integer roleId, Integer id);
}
