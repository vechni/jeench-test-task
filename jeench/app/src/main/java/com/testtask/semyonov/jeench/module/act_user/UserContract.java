package com.testtask.semyonov.jeench.module.act_user;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;

interface UserContract
{
    interface View
            extends MvpBaseView
    {
        void navigateToAlbumsScreen();

        void navigateToPostsScreen();
    }


    interface Presenter
    {
        void onClickBtnAlbums();

        void onClickBtnPosts();
    }
}