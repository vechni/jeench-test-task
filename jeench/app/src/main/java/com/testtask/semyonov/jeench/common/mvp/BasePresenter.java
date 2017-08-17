package com.testtask.semyonov.jeench.common.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.JeenchApplication;
import com.testtask.semyonov.jeench.di.component.ComponentPresenter;
import com.testtask.semyonov.jeench.di.component.DaggerComponentPresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends MvpBaseView>
        implements MvpBasePresenter<V>
{
    private MvpBaseView view;
    private ComponentPresenter component;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    protected ComponentPresenter getPresenterComponent(){
        if( component == null ){
            component = DaggerComponentPresenter.builder()
                    .componentApplication(JeenchApplication.getComponentApplication())
                    .build();
        }

        return component;
    }

    protected void unsubscribeOnDestroy( @NonNull final Disposable disposable ){
        compositeSubscription.add(disposable);
    }

    @CallSuper
    @Override
    public void attachView( @NonNull final V view ){
        this.view = view;
    }

    @CallSuper
    @Override
    public void detachView(){
        compositeSubscription.clear();
        view = null;
    }

    @CallSuper
    @Override
    public void onStart(){

    }

    @CallSuper
    @Override
    public void onStop(){

    }

    @CallSuper
    @Override
    public void onResume(){

    }

    @CallSuper
    @Override
    public void onPause(){

    }
}
