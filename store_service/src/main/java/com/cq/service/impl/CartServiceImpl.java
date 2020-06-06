package com.cq.service.impl;

import com.cq.dao.CartDao;
import com.cq.pojo.Cart;
import com.cq.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Override
    public List<Cart> findByUid(Integer id) {
        return cartDao.findByUid(id);
    }

    @Override
    public void save(Cart cart) {
        cartDao.save(cart);
    }

    @Override
    public void update(Integer id, Integer goods_num, Double member_goods_price) {
        cartDao.update(id,goods_num,member_goods_price);
    }

    @Override
    public Integer findCount(Integer id) {
        return cartDao.findCount(id);
    }

    @Override
    public Cart findById(Integer id) {
        return cartDao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        cartDao.delete(id);
    }

    @Override
    public void deleteMany(String[] ids) {
        if (ids!=null){
            for (String id : ids) {
                cartDao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public Cart findByUidAndGid(Integer id, Integer goods_id) {
        return cartDao.findByUidAndGid(id,goods_id);
    }

    @Override
    public void deleteAllCart(Integer id) {
        cartDao.deleteAllCart(id);
    }
}
