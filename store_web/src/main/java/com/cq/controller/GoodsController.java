package com.cq.controller;

import com.cq.pojo.Goods;
import com.cq.service.GoodsService;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/goods")
//@Secured({"ROLE_ADMIN","ROLE_USER"})
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    HttpSession session;
    @RequestMapping("/findAll")
    public ModelAndView findAll(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    ){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Goods> pageInfo = goodsService.findByPageHelper(pageNum,pageSize);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("goods-list");
        return modelAndView;
    }

    @RequestMapping("/main")
    public ModelAndView main(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    ){
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Goods> pageInfo = goodsService.findByPageHelper(pageNum,pageSize);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("main");
        return modelAndView;
    }
    @RequestMapping("/save")
    public String save(Goods goods){
        System.out.println(goods);
        goods.setOriginal_img(session.getAttribute("imagesPath").toString());
        System.out.println(goods.getOriginal_img());
        goodsService.save(goods);
        return "redirect:/goods/findAll";
    }

    @RequestMapping("/updateUI")
    public ModelAndView updateUI(Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Goods goods = goodsService.findById(id);
        modelAndView.addObject("goods",goods);
        modelAndView.setViewName("goods-update");
        return modelAndView;
    }

    @RequestMapping("/update")
    public String update(Goods goods){
        System.out.println(goods);
        System.out.println(session.getAttribute("imagesPath").toString());
        goods.setOriginal_img(session.getAttribute("imagesPath").toString());
        goodsService.update(goods);
        System.out.println(goods);
        return "redirect:/goods/findAll";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        goodsService.delete(id);
        return "1";
//        return "redirect:/goods/findAll";
    }

    @RequestMapping("/deleteMany")
    @ResponseBody
    public String deleteMany(HttpServletRequest request){
        String[] ids = request.getParameterValues("checkbox");
        goodsService.deleteMany(ids);
        return ids.length+"";
//        return "redirect:/goods/findAll";
    }

}
