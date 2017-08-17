package com.testtask.semyonov.jeench.module.act_album_detail;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;
import com.testtask.semyonov.jeench.module.act_album_detail.item.ContentAlbumViewModel;

import java.util.List;

interface AlbumDetailContract
{
    interface View
            extends MvpBaseView
    {
        void updateContentAlbum( @NonNull List<ContentAlbumViewModel> items );

        void hideLoadIndicator();
    }


    interface Presenter
    {
        void onRefresh();
    }
}