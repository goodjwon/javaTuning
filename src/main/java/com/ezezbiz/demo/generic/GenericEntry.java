package com.ezezbiz.demo.generic;

import java.io.Serializable;

public class GenericEntry <T> {
    private T data;
    private final int rank;

    public GenericEntry(int rank) {
        this.rank = rank;
    }

    public GenericEntry(T data, int rank) {
        this.data = data;
        this.rank = rank;
    }

    public <E extends Rankable & Serializable>GenericEntry(E element) {
        this.data = (T) element;
        this.rank = element.getRank();
    }

    public int getRank() {
        return this.rank;
    }

    public T getDate() {
        return this.data;
    }
}
