package com.example.respodentapp.data.model;

public class Case {
    String Date; String ResponceTime; String Discription;

    public Case() {
        Date = "";
        ResponceTime = "";
        Discription = "";
    }

    public Case(String date, String responceTime, String discription) {

        Date = date;
        ResponceTime = responceTime;
        Discription = discription;
        //this.thisRespondent = thisRespondent;
    }


    public void setDate(String date) {
        Date = date;
    }

    public void setResponceTime(String responceTime) {
        ResponceTime = responceTime;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }



    public String getDate() {
        return Date;
    }

    public String getResponceTime() {
        return ResponceTime;
    }

    public String getDiscription() {
        return Discription;
    }


}
