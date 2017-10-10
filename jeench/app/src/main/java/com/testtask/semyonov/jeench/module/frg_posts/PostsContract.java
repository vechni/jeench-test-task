package com.testtask.semyonov.jeench.module.frg_posts;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;
import com.testtask.semyonov.jeench.module.frg_posts.item.PostViewModel;

import java.util.List;

interface PostsContract
{
    interface View
            extends MvpBaseView
    {
        void updatePosts( @NonNull List<PostViewModel> dataSet );

        void navigateToAddPostScreen( int userId );
    }


    interface Presenter
    {
        void onClickBtnAdd();
    }
}