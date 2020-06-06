package com.cq.service;

import com.cq.pojo.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findByUid(Integer id);

    void update(Integer id,Integer goods_num,Double member_goods_price);

    void save(Cart cart);

    Integer findCount(Integer id);

    Cart findById(Integer id);

    void delete(Integer id);

    void deleteMany(String[] ids);

    Cart findByUidAndGid(Integer id, Integer goods_id);

    void deleteAllCart(Integer id);
}
