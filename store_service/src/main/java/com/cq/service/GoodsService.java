package com.cq.service;

import com.cq.pojo.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface GoodsService {
    List<Goods> findAll();

    void save(Goods goods);

    Goods findById(Integer id);

    void update(Goods goods);

    void delete(Integer id);

    PageInfo<Goods> findByPageHelper(Integer pageNum, Integer pageSize);

    void deleteMany(String[] ids);

}
