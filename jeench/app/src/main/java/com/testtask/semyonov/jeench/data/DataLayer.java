package com.testtask.semyonov.jeench.data;

import com.testtask.semyonov.jeench.data.rest.RestClient;

import javax.inject.Inject;

public class DataLayer
{
    @Inject public RestClient rest;

    @Inject
    public DataLayer(){

    }
}
