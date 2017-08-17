package com.testtask.semyonov.jeench.di.component;

import android.app.Activity;

import com.testtask.semyonov.jeench.common.mvp.BaseActivity;
import com.testtask.semyonov.jeench.di.module.ModuleActivity;
import com.testtask.semyonov.jeench.di.module.ModuleUiRouter;
import com.testtask.semyonov.jeench.di.scope.ScopeActivity;
import com.testtask.semyonov.jeench.module.UiRouter;

import dagger.Component;

@ScopeActivity
@Component( dependencies = {ComponentApplication.class}, modules = {ModuleActivity.class, ModuleUiRouter.class,} )
public interface ComponentActivity
{
    Activity activity();

    UiRouter uiRouter();

    void inject( BaseActivity activity );
}
