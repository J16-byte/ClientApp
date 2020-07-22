package com.example.respodentapp.data.model;

public class Coordinates {
    double latitude;
    double longitude;

    public Coordinates() {
        this.latitude = -1.0;
        this.longitude = 1.0;
    }

    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
