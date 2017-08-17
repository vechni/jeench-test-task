package com.testtask.semyonov.jeench.data.model.user;

import com.google.gson.annotations.SerializedName;

public class UserDTO
{
    @SerializedName( "id" )
    private int id;
    @SerializedName( "name" )
    private String name;
    @SerializedName( "username" )
    private String username;
    @SerializedName( "email" )
    private String email;
    @SerializedName( "address" )
    private Address address;
    @SerializedName( "phone" )
    private String phone;
    @SerializedName( "website" )
    private String website;
    @SerializedName( "company" )
    private Company company;

    public int getId(){ return id;}

    public String getName(){ return name;}

    public String getUsername(){ return username;}

    public String getEmail(){ return email;}

    public Address getAddress(){ return address;}

    public String getPhone(){ return phone;}

    public String getWebsite(){ return website;}

    public Company getCompany(){ return company;}
}
