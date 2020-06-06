package com.cq.controller;

import com.cq.pojo.Permission;
import com.cq.pojo.Role;
import com.cq.service.PermissionService;
import com.cq.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
//@Secured({"ROLE_ADMIN"})
public class RoleController {

    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/savePermissionUI")
    public ModelAndView savePermissionUI(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.addObject("permissionList",permissionList);
        Role role = roleService.findById(id);
        StringBuffer sb = new StringBuffer();
        List<Permission> permissionList1 = role.getPermissionList();
        for (Permission permission : permissionList1) {
            sb.append(",");
            sb.append(permission.getId());
            sb.append(",");
        }
        modelAndView.addObject("role",role);
        modelAndView.addObject("str",sb);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }
    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(Integer roleId,Integer[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:/role/findAll";
    }
}
