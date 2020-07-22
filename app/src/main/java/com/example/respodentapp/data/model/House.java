package com.example.respodentapp.data.model;

public class House {
    Coordinates locatioin;

    public void setLocatioin(Coordinates locatioin) {
        this.locatioin = locatioin;
    }

    public Coordinates getLocatioin() {
        return locatioin;
    }

    public House(Coordinates locatioin) {
        this.locatioin = locatioin;
    }
}
