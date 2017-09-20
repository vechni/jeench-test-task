package com.testtask.semyonov.jeench.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.data.rest.retrofit.ConnectivityInterceptor;
import com.testtask.semyonov.jeench.data.rest.retrofit.RestApi;
import com.testtask.semyonov.jeench.utils.NetworkUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ModuleRetrofit
{
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;
    private HttpLoggingInterceptor logging = null;

    public ModuleRetrofit( @NonNull final Context context ){
        addHttpLogging();
        initHttpClient(context);
        initRetrofit();
    }

    private void addHttpLogging(){
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private void initHttpClient( @NonNull final Context context ){
        okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(logging)
                .addInterceptor(new ConnectivityInterceptor(context))
                .connectTimeout(NetworkUtils.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(NetworkUtils.READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(NetworkUtils.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(NetworkUtils.URL_BACKEND)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    RestApi provideRestApi(){
        return retrofit.create(RestApi.class);
    }
}