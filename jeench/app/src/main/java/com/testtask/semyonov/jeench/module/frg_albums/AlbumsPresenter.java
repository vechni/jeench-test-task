package com.testtask.semyonov.jeench.module.frg_albums;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.data.exeptions.NoConnectivityException;
import com.testtask.semyonov.jeench.data.model.album.AlbumDTO;
import com.testtask.semyonov.jeench.module.frg_albums.AlbumsContract.View;
import com.testtask.semyonov.jeench.module.frg_albums.item.AlbumViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AlbumsPresenter
        extends BasePresenter<View>
        implements AlbumsContract.Presenter
{
    public static final String TAG = AlbumsPresenter.class.getSimpleName();
    private View view;
    private int userId;

    @Inject DataLayer dataLayer;

    AlbumsPresenter( final int userId ){
        getPresenterComponent().inject(this);
        this.userId = userId;
    }

    @Override
    public void attachView( @NonNull final View view ){
        super.attachView(view);
        this.view = view;
    }

    @Override
    public void onStart(){
        super.onStart();
        unsubscribeOnDestroy(requestAlbums());
    }

    @Override
    public void onClickItemAlbum( @NonNull final AlbumViewModel model ){
        view.navigateToAlbumDetailScreen(model);
    }

    @NonNull
    private Disposable requestAlbums(){
        return dataLayer.rest.requestAlbums(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    view.updateInfoAlbums(mapToViewModel(response));
                }, e->{
                    if( e instanceof NoConnectivityException ){
                        view.showToastShort(e.getMessage());
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                });
    }

    @NonNull
    private List<AlbumViewModel> mapToViewModel( @NonNull final List<AlbumDTO> albums ){
        final List<AlbumViewModel> list = new ArrayList<>();
        for( final AlbumDTO album : albums ){
            list.add(new AlbumViewModel(album.getId(), album.getTitle()));
        }
        return list;
    }
}
