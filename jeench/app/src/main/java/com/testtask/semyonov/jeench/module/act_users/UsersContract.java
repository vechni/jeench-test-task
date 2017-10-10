package com.testtask.semyonov.jeench.module.act_users;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;
import com.testtask.semyonov.jeench.module.act_users.item.UserViewModel;

import java.util.List;

interface UsersContract
{
    interface View
            extends MvpBaseView
    {
        void updateUsers( @NonNull List<UserViewModel> items );

        void navigateToUserScreen( @NonNull UserViewModel model );

        void hideLoadIndicator();
    }


    interface Presenter
    {
        void onRefresh();

        void onClickItemUser( @NonNull UserViewModel model );
    }
}