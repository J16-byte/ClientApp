package com.example.respodentapp.data.model;

public class ResLocaton {
    private Respondent resp;
    private Car car;
    private Coordinates location;
    public ResLocaton(Respondent resp, Car car, Coordinates location) {
        this.resp = resp;
        this.car = car;
        this.location = location;
    }

    public Respondent getResp() {
        return resp;
    }

    public Car getCar() {
        return car;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setResp(Respondent resp) {
        this.resp = resp;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }
}
