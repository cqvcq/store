package com.cq.controller;

import com.cq.pojo.Result;
import com.cq.util.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    HttpSession session;

    @RequestMapping("/send")
    @ResponseBody
    public Result sendMailVerifyCode(String emailAddress){
//        System.out.println(emailAddress);
        String verifyCode = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
//        String verifyCode = "123456";
        System.out.println(verifyCode);
        session.setAttribute("verifyCode",verifyCode);
        session.setMaxInactiveInterval(600);
        String content = "邮箱验证码"+verifyCode;
        try {
            MailUtils.sendMailText("邮箱验证码",content,emailAddress);
            return new Result(true,"验证码发送成功",verifyCode);
        } catch (Exception e) {
            return new Result(false,"验证码发送失败");
        }
    }
}
