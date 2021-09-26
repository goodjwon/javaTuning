package com.ezezbiz.demo.generic;

import java.io.Serializable;

public class Product implements Rankable, Serializable {
    private final String name;
    private final double price;
    private int sales;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    @Override
    public int getRank() {
        return sales;
    }
}
