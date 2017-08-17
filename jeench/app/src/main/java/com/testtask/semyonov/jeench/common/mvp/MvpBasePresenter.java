package com.testtask.semyonov.jeench.common.mvp;

import android.support.annotation.NonNull;

public interface MvpBasePresenter<V extends MvpBaseView>
{
    void attachView( @NonNull V view );

    void detachView();

    void onStart();

    void onStop();

    void onResume();

    void onPause();
}
