package com.cq.pojo;

public class Cart {
    private Long id;
    private Long user_id;
    private Long goods_id;
    private String goods_name;
    private String original_img;
    private Double market_price;
    private Double goods_price;
    private Integer goods_num;
    private Double member_goods_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public Double getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Double market_price) {
        this.market_price = market_price;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public Integer getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(Integer goods_num) {
        this.goods_num = goods_num;
    }

    public Double getMember_goods_price() {
        return member_goods_price;
    }

    public void setMember_goods_price(Double member_goods_price) {
        this.member_goods_price = member_goods_price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", original_img='" + original_img + '\'' +
                ", market_price=" + market_price +
                ", goods_price=" + goods_price +
                ", goods_num=" + goods_num +
                ", member_goods_price=" + member_goods_price +
                '}';
    }
}
