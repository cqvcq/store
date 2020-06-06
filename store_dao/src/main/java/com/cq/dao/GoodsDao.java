package com.cq.dao;

import com.cq.pojo.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodsDao {
    @Select("select * from goods")
    public List<Goods> findAll();

    @Insert("insert into goods values(null,#{goods_name},#{market_price},#{shop_price},#{goods_content},#{original_img},#{is_hot},1)")
    void save(Goods goods);

    @Select("select * from goods where goods_id = #{goods_id}")
    Goods findById(Integer id);

    @Update("update goods set goods_name=#{goods_name},market_price=#{market_price},shop_price=#{shop_price},goods_content=#{goods_content},original_img=#{original_img},original_img=#{original_img} where goods_id = #{goods_id}")
    void update(Goods goods);

    @Delete("delete from goods where goods_id = #{goods_id}")
    void delete(Integer id);

}
