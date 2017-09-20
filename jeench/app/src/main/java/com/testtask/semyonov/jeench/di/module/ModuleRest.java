package com.testtask.semyonov.jeench.di.module;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.data.rest.retrofit.RestApi;
import com.testtask.semyonov.jeench.data.rest.RestClient;
import com.testtask.semyonov.jeench.data.rest.retrofit.RestClientImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module( includes = ModuleRetrofit.class )
public class ModuleRest
{
    @Provides
    @Singleton
    RestClient providesRestClient( @NonNull final RestApi restApi ){
        return new RestClientImpl(restApi);
    }
}
