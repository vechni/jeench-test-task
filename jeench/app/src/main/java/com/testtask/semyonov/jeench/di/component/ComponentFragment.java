package com.testtask.semyonov.jeench.di.component;

import android.support.v4.app.Fragment;

import com.testtask.semyonov.jeench.common.mvp.BaseDialogFragment;
import com.testtask.semyonov.jeench.common.mvp.BaseFragment;
import com.testtask.semyonov.jeench.di.module.ModuleFragment;
import com.testtask.semyonov.jeench.di.scope.ScopeFragment;

import dagger.Component;

@ScopeFragment
@Component( dependencies = {ComponentActivity.class}, modules = {ModuleFragment.class} )
public interface ComponentFragment
{
    Fragment fragment();

    void inject( BaseFragment fragment );

    void inject( BaseDialogFragment fragment );
}
