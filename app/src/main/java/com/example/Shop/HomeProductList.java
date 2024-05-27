package com.example.Shop;

public class HomeProductList {
    private String name;
    private String price;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }


    public HomeProductList(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;

    }
}
