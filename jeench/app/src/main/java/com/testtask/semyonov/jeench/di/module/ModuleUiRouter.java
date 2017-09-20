package com.testtask.semyonov.jeench.di.module;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.common.mvp.BaseActivity;
import com.testtask.semyonov.jeench.di.scope.ScopeActivity;
import com.testtask.semyonov.jeench.module.UiRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleUiRouter
{
    @Provides
    @ScopeActivity
    public UiRouter getUiRouter( @NonNull final Activity activity ){
        return new UiRouter(((BaseActivity) activity).getSupportFragmentManager(), activity);
    }
}
