package com.testtask.semyonov.jeench.module.dialog_post;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;

interface PostContract
{
    interface View
            extends MvpBaseView
    {
        void closeDialog();
    }


    interface Presenter
    {
        void onConfirmAddPostClicked( @NonNull String title, @NonNull String body );

        void onCancelClicked();
    }
}
