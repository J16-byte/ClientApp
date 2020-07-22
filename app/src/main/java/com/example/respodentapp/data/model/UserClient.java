package com.example.respodentapp.data.model;

import android.app.Application;

public class UserClient extends Application {
    private ResLocaton user;

    public ResLocaton getUser() {
        return user;
    }

    public void setUser(ResLocaton user) {
        this.user = user;
    }
}
