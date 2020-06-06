package com.cq.service.impl;

import com.cq.dao.CartDao;
import com.cq.dao.GoodsDao;
import com.cq.pojo.Goods;
import com.cq.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    CartDao cartDao;
    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public void save(Goods goods) {
        goodsDao.save(goods);
    }

    @Override
    public Goods findById(Integer id) {
        return goodsDao.findById(id);
    }

    @Override
    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    @Override
    public void delete(Integer id) {
        cartDao.deleteAllCartByGid(id);
        goodsDao.delete(id);
    }

    @Override
    public PageInfo<Goods> findByPageHelper(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goodsList = goodsDao.findAll();
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList,5);
        return pageInfo;
    }

    @Override
    public void deleteMany(String[] ids) {
        if (ids!=null){
            for (String id : ids) {
                cartDao.deleteAllCartByGid(Integer.parseInt(id));
                goodsDao.delete(Integer.parseInt(id));
            }
        }
    }
}
