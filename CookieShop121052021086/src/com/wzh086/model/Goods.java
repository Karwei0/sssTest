package com.wzh086.model;

//g.isScroll isScroll isNew isHot
public class Goods {
    private int id;
    private String name;
    private String cover;
    private String image1;
    private String image2;
    private float price;
    private String intro;
    private int stock;
    private int type_id;
    private String typeName;
    private String recommend_type_str;
    private Type type;
    private boolean isScroll;
    private boolean isNew;
    private boolean isHot;
    public boolean getIsScroll(){
        return recommend_type_str.contains("1");
        //return true;
    }
    public boolean getIsNew(){
        return recommend_type_str.contains("3");
        //return true;
    }
    public boolean getIsHot(){
        return recommend_type_str.contains("2");
        //return true;
    }
    public Goods() {}

    public Goods(int id, String name, String cover, String image1, String image2, float price, String intro, int stock, int type_id, Type type) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.image1 = image1;
        this.image2 = image2;
        this.price = price;
        this.intro = intro;
        this.stock = stock;
        this.type_id = type_id;
        this.type = type;
    }

    public Goods(int id, String name, String cover, String image1, String image2, float price, String intro, int stock, int type_id, String typeName, String recommend_type_str, Type type, boolean isScroll, boolean isNew, boolean isHot) {
        this.id = id;
        this.name = name;
        this.cover = cover;
        this.image1 = image1;
        this.image2 = image2;
        this.price = price;
        this.intro = intro;
        this.stock = stock;
        this.type_id = type_id;
        this.typeName = typeName;
        this.recommend_type_str = recommend_type_str;
        this.type = type;
        this.isScroll = isScroll;
        this.isNew = isNew;
        this.isHot = isHot;
    }

    public String getRecommend_type_str() {
        return recommend_type_str;
    }

    public void setRecommend_type_str(String recommend_type_str) {
        this.recommend_type_str = recommend_type_str;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getName() {
        return name;
    }

    public String getCover() {
        return cover;
    }

    public String getImage1() {
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public float getPrice() {
        return price;
    }

    public String getIntro() {
        return intro;
    }

    public int getStock() {
        return stock;
    }

    public int getType_id() {
        return type_id;
    }

    public Type getType() {
        if(this.type == null){
            this.type = new Type();
            this.type.setName(typeName);
            this.type.setId(type_id);
        }
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
