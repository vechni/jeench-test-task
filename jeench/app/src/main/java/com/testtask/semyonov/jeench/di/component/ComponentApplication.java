package com.testtask.semyonov.jeench.di.component;

import android.content.Context;

import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.di.module.ModuleApplication;
import com.testtask.semyonov.jeench.di.module.ModuleRest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component( modules = {ModuleApplication.class, ModuleRest.class} )
public interface ComponentApplication
{
    Context context();

    DataLayer dataLayer();
}

