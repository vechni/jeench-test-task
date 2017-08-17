package com.testtask.semyonov.jeench.module.frg_posts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.interfaces.OnBackPressedListener;
import com.testtask.semyonov.jeench.common.lib.Dips;
import com.testtask.semyonov.jeench.common.mvp.BaseFragment;
import com.testtask.semyonov.jeench.module.frg_posts.item.PostViewModel;
import com.testtask.semyonov.jeench.module.frg_posts.item.PostsAdapter;
import com.testtask.semyonov.jeench.utils.AppConst;
import com.testtask.semyonov.jeench.utils.AppKeys;
import com.testtask.semyonov.jeench.views.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostsFragment
        extends BaseFragment
        implements PostsContract.View, OnBackPressedListener
{
    private final String TAG = PostsFragment.class.getSimpleName();

    @BindView( R.id.fragment_products_rv ) RecyclerView recyclerView;
    @BindView( R.id.fragment_products_btn_add ) FloatingActionButton btnAdd;
    private PostsAdapter adapter;
    private PostsPresenter presenter;
    private int userId = AppConst.DEFAULT_ID;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if( args != null ){
            userId = args.getInt(AppKeys.USER_ID, AppConst.DEFAULT_ID);
        }
        presenter = new PostsPresenter(userId);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        super.onCreateView(inflater, container, savedInstanceState);
        final View root = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, root);

        btnAdd.setVisibility(View.VISIBLE);

        adapter = new PostsAdapter();

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

    @OnClick( R.id.fragment_products_btn_add )
    public void onButtonAddPostClicked( final View view ){
        presenter.onButtonAddPostClicked();
    }

    @Override
    public void updatePosts( @NonNull final List<PostViewModel> items ){
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void openAddPostView( final int userId ){
        uiRouter.openAddPostView(userId);
    }
}
