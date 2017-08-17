package com.testtask.semyonov.jeench.module.frg_albums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.interfaces.OnBackPressedListener;
import com.testtask.semyonov.jeench.common.lib.Dips;
import com.testtask.semyonov.jeench.common.mvp.BaseFragment;
import com.testtask.semyonov.jeench.module.frg_albums.item.AlbumAdapter;
import com.testtask.semyonov.jeench.module.frg_albums.item.AlbumViewModel;
import com.testtask.semyonov.jeench.utils.AppConst;
import com.testtask.semyonov.jeench.utils.AppKeys;
import com.testtask.semyonov.jeench.views.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsFragment
        extends BaseFragment
        implements AlbumsContract.View, OnBackPressedListener
{
    private final String TAG = AlbumsFragment.class.getSimpleName();

    @BindView( R.id.fragment_products_rv ) RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private AlbumsPresenter presenter;
    private int userId = AppConst.DEFAULT_ID;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        if( args != null ){
            userId = args.getInt(AppKeys.USER_ID, AppConst.DEFAULT_ID);
        }
        presenter = new AlbumsPresenter(userId);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        super.onCreateView(inflater, container, savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, root);

        adapter = new AlbumAdapter(listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration((int) Dips.toPix(activity, 10), 0, 0, 0));
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onStart(){
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onBackPressed(){
        activity.finish();
    }

    AlbumAdapter.Listener listener = new AlbumAdapter.Listener()
    {
        @Override
        public void onAlbumClicked( @NonNull final AlbumViewModel model ){
            presenter.onAlbumClicked(model);
        }
    };

    @Override
    public void updateInfoAlbums( @NonNull final List<AlbumViewModel> items ){
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openAlbumDetailView( @NonNull final AlbumViewModel model ){
        uiRouter.openAlbumDetailView(model.getId());
    }
}
