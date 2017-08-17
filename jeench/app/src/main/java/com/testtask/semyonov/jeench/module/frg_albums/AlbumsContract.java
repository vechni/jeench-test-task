package com.testtask.semyonov.jeench.module.frg_albums;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.testtask.semyonov.jeench.common.mvp.MvpBaseView;
import com.testtask.semyonov.jeench.module.frg_albums.item.AlbumViewModel;

import java.util.List;

interface AlbumsContract
{
    interface View
            extends MvpBaseView
    {
        void updateInfoAlbums( @NonNull List<AlbumViewModel> items );

        void openAlbumDetailView( @NonNull AlbumViewModel model );
    }


    interface Presenter
    {
        void onAlbumClicked( @NonNull AlbumViewModel model );
    }
}