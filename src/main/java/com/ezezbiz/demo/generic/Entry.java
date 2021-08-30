package com.ezezbiz.demo.generic;

import java.io.Serializable;

public class Entry {
    private final String data;
    private final int rank;

    public Entry(String data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    public <E extends Rankable & Serializable> Entry(E element) {
        this.data = element.toString();
        this.rank = element.getRank();
    }

    public String getData() {
        return data;
    }

    public int getRank() {
        return rank;
    }
}
