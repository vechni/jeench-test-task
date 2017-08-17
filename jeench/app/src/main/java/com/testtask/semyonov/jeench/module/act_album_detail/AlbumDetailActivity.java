package com.testtask.semyonov.jeench.module.act_album_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.lib.Dips;
import com.testtask.semyonov.jeench.common.mvp.BaseActivity;
import com.testtask.semyonov.jeench.module.act_album_detail.item.ContentAlbumAdapter;
import com.testtask.semyonov.jeench.module.act_album_detail.item.ContentAlbumViewModel;
import com.testtask.semyonov.jeench.utils.AppConst;
import com.testtask.semyonov.jeench.utils.AppKeys;
import com.testtask.semyonov.jeench.views.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailActivity
        extends BaseActivity
        implements AlbumDetailContract.View
{
    private final String TAG = AlbumDetailActivity.class.getSimpleName();
    public static final int SPAN_COUNT = 2;

    @BindView( R.id.activity_list_rv ) RecyclerView recyclerView;
    @BindView( R.id.activity_list_swipe_refresh ) SwipeRefreshLayout swipeContainer;
    private ContentAlbumAdapter adapter;
    private AlbumDetailPresenter presenter;
    private int albumId = AppConst.DEFAULT_ID;

    @Override
    protected void onCreate( final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Bundle extras = getIntent().getExtras();
        if( extras != null ){
            albumId = extras.getInt(AppKeys.ALBUM_ID, AppConst.DEFAULT_ID);
        }

        presenter = new AlbumDetailPresenter(albumId);
        presenter.attachView(this);

        ButterKnife.bind(this);

        adapter = new ContentAlbumAdapter();

        recyclerView.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        recyclerView.addItemDecoration(new SpacesItemDecoration((int) Dips.toPix(this, 5),
                                                                (int) Dips.toPix(this, 10),
                                                                (int) Dips.toPix(this, 10),
                                                                (int) Dips.toPix(this, 5)));
        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(()->presenter.onRefresh());
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void updateContentAlbum( @NonNull final List<ContentAlbumViewModel> items ){
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void hideLoadIndicator(){
        swipeContainer.setRefreshing(false);
    }
}