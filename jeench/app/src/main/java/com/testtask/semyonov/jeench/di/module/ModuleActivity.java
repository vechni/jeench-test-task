package com.testtask.semyonov.jeench.di.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleActivity
{
    private final Activity activity;

    public ModuleActivity( @NonNull final Activity activity ){
        this.activity = activity;
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }
}
