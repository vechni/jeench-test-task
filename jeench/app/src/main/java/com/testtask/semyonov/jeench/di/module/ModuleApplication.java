package com.testtask.semyonov.jeench.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.testtask.semyonov.jeench.module.UiRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleApplication
{
    private final Application application;

    public ModuleApplication( @NonNull final Application application ){
        this.application = application;
    }

    @Provides
    Context provideContext(){
        return application.getApplicationContext();
    }
}
