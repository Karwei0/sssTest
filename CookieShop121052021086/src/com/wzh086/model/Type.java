package com.wzh086.model;

public class Type {
    private int id;
    private String name;
    private String encodeName;


    public Type() {
    }

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getEncodeName() {
        return encodeName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEncodeName(String encodeName) {
        this.encodeName = encodeName;
    }
}
