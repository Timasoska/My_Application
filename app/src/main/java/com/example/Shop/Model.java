package com.example.Shop;

import android.widget.ImageView;

import java.util.Comparator;

public class Model
{
    String name,desc,price;
    ImageView imageUrl;

    public Model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public Model(String name, String desc, String price) {
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
}
