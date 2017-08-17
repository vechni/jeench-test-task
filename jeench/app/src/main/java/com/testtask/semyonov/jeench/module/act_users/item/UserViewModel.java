package com.testtask.semyonov.jeench.module.act_users.item;

import android.support.annotation.NonNull;

public class UserViewModel
{
    private int id;
    @NonNull private String name;
    @NonNull private String email;
    @NonNull private String address;

    public UserViewModel( @NonNull final int id,
                          @NonNull final String name,
                          @NonNull final String email,
                          @NonNull final String address ){
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getId(){
        return id;
    }

    @NonNull
    public String getName(){
        return name;
    }

    @NonNull
    public String getEmail(){
        return email;
    }

    @NonNull
    public String getAddress(){
        return address;
    }
}
