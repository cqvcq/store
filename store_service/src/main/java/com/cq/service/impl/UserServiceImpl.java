package com.cq.service.impl;

import com.cq.dao.UserDao;
import com.cq.pojo.Cart;
import com.cq.pojo.Role;
import com.cq.pojo.SysUser;
import com.cq.service.CartService;
import com.cq.service.RoleService;
import com.cq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CartService cartService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userDao.findByUsername(username);
        if(sysUser!=null){
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            List<Role> roleList = sysUser.getRoleList();
            for (Role role : roleList) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
            }
            UserDetails user = new User(sysUser.getUsername(),sysUser.getPassword(),authorities);
            return user;
        }else{
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
            authorities.add(grantedAuthority);
            UserDetails user = new User(sysUser.getUsername(),sysUser.getPassword(), authorities);
            return user;
        }
    }


    @Override
    public List<SysUser> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(SysUser sysUser) {
        String password = sysUser.getPassword();
        String encode = passwordEncoder.encode(password);
        sysUser.setPassword(encode);
        userDao.save(sysUser);

    }

    @Override
    public boolean isUniqueUsername(String username) {
        SysUser byUsername = userDao.findByUsername(username);
        if(byUsername == null){
            return true;
        }
        return false;
    }

    @Override
    public SysUser findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] ids) {
        userDao.deleteAllRole(userId);
        if(ids!=null){
            for (Integer id : ids) {
                userDao.addRoleToUser(userId,id);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        cartService.deleteAllCart(id);
        userDao.deleteAllRole(id);
        userDao.delete(id);
    }

    @Override
    public Integer findIdByUsername(String username) {
        return userDao.findIdByUsername(username);
    }

    @Override
    public void register(SysUser sysUser) {
        String password = sysUser.getPassword();
        String encode = passwordEncoder.encode(password);
        sysUser.setPassword(encode);
        sysUser.setStatus(1);
        userDao.save(sysUser);
        Integer id = userDao.findIdByUsername(sysUser.getUsername());
        userDao.addRoleToUser(id,2);
    }

}
