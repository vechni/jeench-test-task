package com.testtask.semyonov.jeench.module.act_users;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;
import com.testtask.semyonov.jeench.module.act_users.item.UserViewModel;

import java.util.List;

interface UsersContract
{
    interface View
            extends MvpBaseView
    {
        void updateUsers( @NonNull List<UserViewModel> items );

        void openUserView( @NonNull UserViewModel model );

        void hideLoadIndicator();
    }


    interface Presenter
    {
        void onRefresh();

        void onUserClicked( @NonNull UserViewModel model );
    }
}