package com.testtask.semyonov.jeench.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.module.act_album_detail.AlbumDetailActivity;
import com.testtask.semyonov.jeench.module.act_user.UserActivity;
import com.testtask.semyonov.jeench.module.dialog_post.PostDialog;
import com.testtask.semyonov.jeench.module.frg_albums.AlbumsFragment;
import com.testtask.semyonov.jeench.module.frg_posts.PostsFragment;
import com.testtask.semyonov.jeench.utils.AppKeys;

public class UiRouter
{
    @NonNull private final FragmentManager fragmentManager;
    @NonNull private final Activity activity;

    public UiRouter( @NonNull final FragmentManager fragmentManager, @NonNull final Activity activity ){
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    private void showFragment( @NonNull final Fragment frg ){
        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_users_detail_frame, frg, frg.getClass().getName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    public void openAlbumsView( final int userId ){
        final Bundle args = new Bundle();
        args.putInt(AppKeys.USER_ID, userId);

        final AlbumsFragment fragment = new AlbumsFragment();
        fragment.setArguments(args);
        showFragment(fragment);
    }

    public void openPostsView( final int userId ){
        final Bundle args = new Bundle();
        args.putInt(AppKeys.USER_ID, userId);

        final PostsFragment fragment = new PostsFragment();
        fragment.setArguments(args);
        showFragment(fragment);
    }

    public void openUserView( final int userId,
                              @NonNull final String name,
                              @NonNull final String email,
                              @NonNull final String address ){
        final Intent intent = new Intent(activity, UserActivity.class);
        intent.putExtra(AppKeys.USER_ID, userId);
        intent.putExtra(AppKeys.NAME, name);
        intent.putExtra(AppKeys.EMAIL, email);
        intent.putExtra(AppKeys.ADDRESS, address);
        activity.startActivity(intent);
    }

    public void openAlbumDetailView( final int albumId ){
        final Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.putExtra(AppKeys.ALBUM_ID, albumId);
        activity.startActivity(intent);
    }


    public void openAddPostView(final int userId){
        final Bundle args = new Bundle();
        args.putInt(AppKeys.USER_ID, userId);

        final PostDialog dialog = new PostDialog();
        dialog.setArguments(args);
        dialog.show(fragmentManager, dialog.getClass().getName());
    }
}
