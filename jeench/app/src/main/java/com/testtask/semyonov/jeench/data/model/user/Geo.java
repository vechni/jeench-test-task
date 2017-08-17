package com.testtask.semyonov.jeench.data.model.user;

import com.google.gson.annotations.SerializedName;

public class Geo
{
    @SerializedName( "lat" )
    private String lat;
    @SerializedName( "lng" )
    private String lng;

    public String getLat(){ return lat;}

    public String getLng(){ return lng;}
}
