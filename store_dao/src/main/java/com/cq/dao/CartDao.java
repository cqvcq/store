package com.cq.dao;

import com.cq.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartDao {

    @Select("select * from cart where user_id = #{id}")
    List<Cart> findByUid(Integer id);
    @Insert("insert into cart values(null,#{user_id},#{goods_id},#{goods_name},#{original_img},#{market_price},#{goods_price},#{goods_num},#{member_goods_price})")
    void save(Cart cart);
    @Update("update cart set goods_num = #{param2},member_goods_price = #{param3} where id = #{param1}")
    void update(Integer id,Integer goods_num,Double member_goods_price);

    @Select("select count(1) from cart where user_id = #{id}")
    Integer findCount(Integer id );

    @Select("select * from cart where id = #{id}")
    Cart findById(Integer id);

    @Delete("delete from cart where id = #{id}")
    void delete(Integer id);

    @Select("select * from cart where user_id = #{param1} and goods_id = #{param2}")
    Cart findByUidAndGid(Integer id, Integer goods_id);

    @Delete("delete from cart where user_id = #{id}")
    void deleteAllCart(Integer id);

    @Delete("delete from cart where goods_id = #{id}")
    void deleteAllCartByGid(Integer id);
}
