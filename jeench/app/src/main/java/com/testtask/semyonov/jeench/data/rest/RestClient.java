package com.testtask.semyonov.jeench.data.rest;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.data.model.album.AlbumDTO;
import com.testtask.semyonov.jeench.data.model.album_detail.AlbumDetailDTO;
import com.testtask.semyonov.jeench.data.model.post.PostDTO;
import com.testtask.semyonov.jeench.data.model.user.UserDTO;

import java.util.List;

import io.reactivex.Observable;

public interface RestClient
{
    Observable<List<UserDTO>> requestUsers();

    Observable<List<PostDTO>> requestPosts( int userId );

    Observable<List<AlbumDTO>> requestAlbums( int userId );

    Observable<List<AlbumDetailDTO>> requestAlbum( int albumId );

    Observable<PostDTO> sendPost( int userId, @NonNull String title, @NonNull String body );
}
