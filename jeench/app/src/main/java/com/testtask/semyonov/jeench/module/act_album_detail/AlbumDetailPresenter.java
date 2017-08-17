package com.testtask.semyonov.jeench.module.act_album_detail;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.data.exeptions.NoConnectivityException;
import com.testtask.semyonov.jeench.data.model.album_detail.AlbumDetailDTO;
import com.testtask.semyonov.jeench.module.act_album_detail.AlbumDetailContract.View;
import com.testtask.semyonov.jeench.module.act_album_detail.item.ContentAlbumViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AlbumDetailPresenter
        extends BasePresenter<View>
        implements AlbumDetailContract.Presenter
{
    public static final String TAG = "tag_settings_prs";

    private View view;
    private int albumId;
    @Inject DataLayer dataLayer;

    AlbumDetailPresenter( final int albumId ){
        getPresenterComponent().inject(this);
        this.albumId = albumId;
    }

    @Override
    public void attachView( @NonNull final View view ){
        super.attachView(view);
        this.view = view;
    }

    @Override
    public void onStart(){
        super.onStart();
        requestContentAlbum();
    }

    @Override
    public void onRefresh(){
        requestContentAlbum();
    }

    private void requestContentAlbum(){
        dataLayer.rest.requestAlbum(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    view.updateContentAlbum(mapToViewModel(response));
                }, e->{
                    if( e instanceof NoConnectivityException ){
                        view.hideLoadIndicator();
                        view.showToastShort(e.getMessage());
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                });
    }

    private List<ContentAlbumViewModel> mapToViewModel( @NonNull final List<AlbumDetailDTO> albumDetails ){
        List<ContentAlbumViewModel> list = new ArrayList<>();
        for( AlbumDetailDTO detail : albumDetails ){
            list.add(new ContentAlbumViewModel(detail.getTitle(), detail.getUrl()));
        }
        return list;
    }
}
