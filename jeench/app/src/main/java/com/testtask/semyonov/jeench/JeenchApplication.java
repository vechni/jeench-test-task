package com.testtask.semyonov.jeench;

import android.app.Application;

import com.testtask.semyonov.jeench.di.component.ComponentApplication;
import com.testtask.semyonov.jeench.di.component.DaggerComponentApplication;
import com.testtask.semyonov.jeench.di.module.ModuleApplication;
import com.testtask.semyonov.jeench.di.module.ModuleRetrofit;

public class JeenchApplication
        extends Application
{
    private static ComponentApplication component;

    @Override
    public void onCreate(){
        super.onCreate();

        component = DaggerComponentApplication.builder()
                .moduleApplication(new ModuleApplication(this))
                .moduleRetrofit(new ModuleRetrofit(this))
                .build();
    }

    public static ComponentApplication getComponentApplication(){
        return component;
    }
}
