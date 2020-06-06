package com.cq.dao;

import com.cq.pojo.SysUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface UserDao {
    @Select("select * from sys_user where username = #{username} and status=1")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.cq.dao.RoleDao.findById",fetchType = FetchType.LAZY))
    })
    SysUser findByUsername(String username);

    @Select("select * from sys_user")
    List<SysUser> findAll();

    @Insert("insert into sys_user values(null,#{username},#{email},#{password},#{status})")
    void save(SysUser sysUser);

    @Select("select * from sys_user where id = #{uid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "roleList",column = "id",javaType = List.class,
            many = @Many(select = "com.cq.dao.RoleDao.findById",fetchType = FetchType.LAZY))
    })
    SysUser findById(Integer uid);

    @Delete("delete from sys_user_role where userId = #{uid}")
    void deleteAllRole(Integer uid);

    @Insert("insert into sys_user_role values(#{param1},#{param2})")
    void addRoleToUser(Integer userId, Integer id);

    @Delete("delete from sys_user where id = #{id}")
    void delete(Integer id);

    @Select("select id from sys_user where username = #{username}")
    Integer findIdByUsername(String username);
}
