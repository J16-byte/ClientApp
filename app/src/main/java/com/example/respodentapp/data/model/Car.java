package com.example.respodentapp.data.model;

public class Car {

    private String RegNo;
    private String Make;
    private String Model;

    public Car() {

        RegNo = "";
        Make = "";
        Model = "";
    }

    public Car(String regNo, String make, String model) {

        RegNo = regNo;
        Make = make;
        Model = model;
    }



    public void setRegNo(String regNo) {
        RegNo = regNo;
    }

    public void setMake(String make) {
        Make = make;
    }

    public void setModel(String model) {
        Model = model;
    }


    public String getRegNo() {
        return RegNo;
    }

    public String getMake() {
        return Make;
    }

    public String getModel() {
        return Model;
    }
}
