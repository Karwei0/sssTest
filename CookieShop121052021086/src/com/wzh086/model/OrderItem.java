package com.wzh086.model;

public class OrderItem {
    private int id;
    private float price;
    private int amount;
    private String name;
    private int goods_id;
    private int order_id;
    private Goods goods;

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Goods getGoods() {
        return goods;
    }

    public String getName() {
        return name;
    }
}
