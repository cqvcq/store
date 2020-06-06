package com.cq.controller;

import com.cq.pojo.Result;
import com.cq.pojo.Role;
import com.cq.pojo.SysUser;
import com.cq.service.PermissionService;
import com.cq.service.RoleService;
import com.cq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
//@Secured({"ROLE_ADMIN"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;
    @Autowired
    HttpSession session;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<SysUser> userList = userService.findAll();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(SysUser sysUser){
        userService.save(sysUser);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/details")
    public ModelAndView details(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        SysUser sysUser = userService.findById(id);
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/saveRoleUI")
    public ModelAndView saveRoleUI(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList",roleList);
        SysUser sysUser = userService.findById(id);
        List<Role> roleList1 = sysUser.getRoleList();
        StringBuffer sb = new StringBuffer();
        for (Role role : roleList1) {
            sb.append(",");
            sb.append(role.getId());
            sb.append(",");
        }
        modelAndView.addObject("sysUser",sysUser);
        modelAndView.addObject("str",sb);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(Integer userId,Integer[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:/user/findAll";
    }
    @RequestMapping("delete")
    public String delete(Integer id){
        userService.delete(id);
        return "redirect:/user/findAll";
    }
    @RequestMapping("register")
    @ResponseBody
    public Result register(SysUser sysUser){
        try {
            System.out.println(sysUser);
            userService.register(sysUser);
            return new Result(true,"注册成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"注册失败");
        }
    }

    @RequestMapping("isEquals")
    @ResponseBody
    public Result register(String verifyCode){
        System.out.println(session.getAttribute("verifyCode"));
        String code = String.valueOf(session.getAttribute("verifyCode"));
        System.out.println(code);
        if(code.equals(verifyCode)){
            return new Result(true,"验证码正确");
        }else{
            return new Result(false,"验证码错误");
        }
    }


    @RequestMapping("isUniqueUsername")
    @ResponseBody
    public Result isUniqueUsername(String username){
        Integer id = userService.findIdByUsername(username);
        if(id==null){
            return new Result(true,"用户名可用");
        }else {
            return new Result(false,"用户名重复");
        }
    }

}
