package com.cq.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/pid")
@Controller
public class PicUpload {
    @RequestMapping(value="/upload",method= RequestMethod.POST)
    @ResponseBody
    private Map<String, Object>  fildUpload(@RequestParam(value="original_img",required=false) MultipartFile file,
                                            HttpServletRequest request,HttpSession session, Model model)throws Exception{
        Map<String, Object> json = new HashMap<String, Object>();
        //基本表单
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            //地址
            path="img/1/"+uuid+"."+imageName;
            file.transferTo(new File(pathRoot+path));
            System.out.println(pathRoot+path);
        }else {
            //可以使用以下包装类。响应结果，请看附件
            //ResponseResult.build(400,"上传图片失败");
        }
        System.out.println(path);
        session.setAttribute("imagesPath", path);
        model.addAttribute("imgPath",path);
        json.put("success", path);
        return json;
    }
}
