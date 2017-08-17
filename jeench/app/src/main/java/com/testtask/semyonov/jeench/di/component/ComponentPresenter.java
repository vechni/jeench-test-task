package com.testtask.semyonov.jeench.di.component;

import com.testtask.semyonov.jeench.di.scope.ScopePresenter;
import com.testtask.semyonov.jeench.module.act_album_detail.AlbumDetailPresenter;
import com.testtask.semyonov.jeench.module.act_user.UserPresenter;
import com.testtask.semyonov.jeench.module.act_users.UsersPresenter;
import com.testtask.semyonov.jeench.module.dialog_post.PostPresenter;
import com.testtask.semyonov.jeench.module.frg_albums.AlbumsPresenter;
import com.testtask.semyonov.jeench.module.frg_posts.PostsPresenter;

import dagger.Component;

@ScopePresenter
@Component( dependencies = ComponentApplication.class )
public interface ComponentPresenter
{
    void inject( AlbumDetailPresenter presenter );

    void inject( UserPresenter presenter );

    void inject( UsersPresenter presenter );

    void inject( AlbumsPresenter presenter );

    void inject( PostsPresenter presenter );

    void inject( PostPresenter presenter );
}
