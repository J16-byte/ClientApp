package com.example.respodentapp.data.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.Date;

public class Respondent{
    private String id;
    private String email;
    private String name;
    private String surname;
 //   private LatLng geo_point;
    private Date timestamp;
   // private Car car;
    ArrayList<Case> cases;

    public Respondent(){};

    public Respondent(String id, String email, String name, String surname, Date timestamp,ArrayList<Case> cases) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
       // this.geo_point = geo_point;
        this.timestamp = timestamp;
        //this.car = car;
        this.cases = cases;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }


    public ArrayList<Case> getCases() {
        return cases;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }


}