package com.wzh086.model;

import com.wzh086.utils.PriceUtils;

import java.util.*;

public class Order {
    private int id;
    private float total;
    private int amount;
    private int status;
    private int paytype;
    private String name;
    private String phone;
    private String address;
    private Date datetime;
    private int user_id;
    private User user;
    private Map<Integer, OrderItem> itemMap = new HashMap<>();
    private List<OrderItem> itemList = new ArrayList<OrderItem>();

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setItemMap(Map<Integer, OrderItem> itemMap) {
        this.itemMap = itemMap;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public float getTotal() {
        return total;
    }

    public int getAmount() {
        return amount;
    }

    public int getStatus() {
        return status;
    }

    public int getPaytype() {
        return paytype;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Date getDatetime() {
        return datetime;
    }

    public int getUser_id() {
        return user_id;
    }

    public Map<Integer, OrderItem> getItemMap() {
        return itemMap;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public User getUser() {
        return user;
    }

    public boolean addGoods(Goods goods){
        if(goods.getStock() > 0){
            if(itemMap.containsKey(goods.getId())){
                OrderItem item = itemMap.get(goods.getId());
                this.amount++;
                this.total = PriceUtils.add(this.total, item.getPrice());
                item.setAmount(item.getAmount() + 1);
            }else {
                OrderItem item = new OrderItem();
                item.setGoods(goods);
                item.setAmount(1);
                item.setPrice(goods.getPrice());
                item.setGoods_id(goods.getId());
                itemMap.put(goods.getId(), item);
                this.total = PriceUtils.add(this.total, item.getPrice());
                this.amount++;
            }
            return true;
        }
        return false;
    }


    public boolean lessenGoods(int goodsid){
            if(itemMap.containsKey(goodsid)){
                OrderItem item = itemMap.get(goodsid);
                this.amount--;
                this.total = (float)PriceUtils.subtract(this.total, item.getPrice());
                item.setAmount(item.getAmount() - 1);
                if(item.getAmount() <= 0){
                    itemMap.remove(goodsid);
                }
                return true;
            }
            return false;
    }

    public boolean removeGoods(int goodsid){
        if(itemMap.containsKey(goodsid)){
            OrderItem item = itemMap.get(goodsid);
            this.amount -= item.getAmount();
            this.total = (float)PriceUtils.subtract(this.total, item.getPrice() * item.getAmount());
            itemMap.remove(goodsid);
            return true;
        }
        return false;
    }
}
