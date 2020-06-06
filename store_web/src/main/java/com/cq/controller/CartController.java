package com.cq.controller;

import com.cq.pojo.Cart;
import com.cq.pojo.Goods;
import com.cq.service.CartService;
import com.cq.service.GoodsService;
import com.cq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
//@Secured({"ROLE_ADMIN","ROLE_USER"})
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Autowired
    GoodsService goodsService;
    @RequestMapping("/findByUid")
    public ModelAndView findByUid(){
        ModelAndView modelAndView = new ModelAndView();
        Object spring_security_context = session.getAttribute("SPRING_SECURITY_CONTEXT");
        SecurityContext securityContext = (SecurityContext) spring_security_context;
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        String username = user.getUsername();
        Integer id = userService.findIdByUsername(username);
        session.setAttribute("id",id);
        List<Cart> cartList = cartService.findByUid(id);
        Integer count = cartService.findCount(id);
        Double d = 0.0;
        for (Cart cart : cartList) {
            d= d+cart.getMember_goods_price();
        }
        modelAndView.addObject("cost",d);
        modelAndView.addObject("cartList",cartList);
        modelAndView.addObject("count",count);
        modelAndView.setViewName("cart-list");
        return modelAndView;
    }

    @RequestMapping("add")
    public String add(Integer id){
        Cart cart = cartService.findById(id);
        Double d = cart.getMember_goods_price()+cart.getGoods_price();
        cartService.update(id,cart.getGoods_num()+1,d);
        return "redirect:/cart/findByUid";
    }
    @RequestMapping("reduce")
    public String reduce(Integer id){
        Cart cart = cartService.findById(id);
        Double d = cart.getMember_goods_price()-cart.getGoods_price();
        cartService.update(id,cart.getGoods_num()-1,d);
        return "redirect:/cart/findByUid";
    }
    @RequestMapping("/save")
    public String save(Integer goods_id){
        Goods goods = goodsService.findById(goods_id);
        Integer id = (Integer) session.getAttribute("id");
        Cart cart = cartService.findByUidAndGid(id,goods_id);
        if(cart == null){
            Cart cart2 = new Cart();
            cart2.setUser_id(Long.valueOf(id));
            cart2.setGoods_id(Long.valueOf(goods_id));
            cart2.setGoods_name(goods.getGoods_name());
            cart2.setOriginal_img(goods.getOriginal_img());
            cart2.setMarket_price(goods.getMarket_price());
            cart2.setGoods_price(goods.getShop_price());
            cart2.setGoods_num(1);
            cart2.setMember_goods_price(goods.getShop_price());
            cartService.save(cart2);
        }
        else{
            Cart cart3 = cartService.findById(cart.getId().intValue());
            Double d = cart3.getMember_goods_price()+cart3.getGoods_price();
            cartService.update(cart.getId().intValue(),cart3.getGoods_num()+1,d);
        }
        return "redirect:/cart/findByUid";
    }
    @RequestMapping("/delete")
    public void delete(Integer id){
        cartService.delete(id);
    }

    @RequestMapping("/deleteMany")
    public void deleteMany(HttpServletRequest request){
        String[] ids = request.getParameterValues("checkbox");
        cartService.deleteMany(ids);
    }

}
