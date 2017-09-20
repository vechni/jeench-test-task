package com.testtask.semyonov.jeench.module.frg_posts;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.data.exeptions.NoConnectivityException;
import com.testtask.semyonov.jeench.data.model.post.PostDTO;
import com.testtask.semyonov.jeench.module.frg_posts.PostsContract.View;
import com.testtask.semyonov.jeench.module.frg_posts.item.PostViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostsPresenter
        extends BasePresenter<View>
        implements PostsContract.Presenter
{
    public static final String TAG = PostsPresenter.class.getSimpleName();
    private View view;
    private int userId;

    @Inject DataLayer dataLayer;

    PostsPresenter( final int userId ){
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
        unsubscribeOnDestroy(requestPosts());
    }

    @Override
    public void onButtonAddPostClicked(){
        view.openAddPostView(userId);
    }

    private Disposable requestPosts(){
        return dataLayer.rest.requestPosts(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    view.updatePosts(mapToViewModel(response));
                }, e -> {
                    if( e instanceof NoConnectivityException ){
                        view.showToastShort(e.getMessage());
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                });
    }

    private List<PostViewModel> mapToViewModel( @NonNull final List<PostDTO> posts ){
        List<PostViewModel> list = new ArrayList<>();
        for( PostDTO post : posts ){
            list.add(new PostViewModel(post.getId(), post.getTitle()));
        }
        return list;
    }
}
