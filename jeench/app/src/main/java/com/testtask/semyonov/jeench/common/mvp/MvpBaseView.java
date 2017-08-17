package com.testtask.semyonov.jeench.common.mvp;

import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public interface MvpBaseView
{
    void openWaitDialog( @NonNull String message, @NonNull OnCancelListener listener );

    void closeWaitDialog();

    void hideKeyboard();

    void showKeyboard();

    void showToastShort( String message );

    void showToastLong( String message );

    void showToastShort( @StringRes int resId );

    void showToastLong( @StringRes int resId );
}
