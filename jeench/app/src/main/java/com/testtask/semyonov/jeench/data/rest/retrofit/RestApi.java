package com.testtask.semyonov.jeench.data.rest.retrofit;

import com.testtask.semyonov.jeench.data.model.album.AlbumDTO;
import com.testtask.semyonov.jeench.data.model.album_detail.AlbumDetailDTO;
import com.testtask.semyonov.jeench.data.model.post.NewPost;
import com.testtask.semyonov.jeench.data.model.post.PostDTO;
import com.testtask.semyonov.jeench.data.model.user.UserDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestApi
{
    @GET( "users" )
    Observable<Response<List<UserDTO>>> requestUsers();

    @GET( "users/{userId}/posts" )
    Observable<Response<List<PostDTO>>> requestPosts( @Path( "userId" ) int id );

    @GET( "users/{userId}/albums" )
    Observable<Response<List<AlbumDTO>>> requestAlbums( @Path( "userId" ) int id );

    @GET( "albums/{albumId}/photos" )
    Observable<Response<List<AlbumDetailDTO>>> requestAlbum( @Path( "albumId" ) int id );

    @POST( "posts" )
    Observable<Response<PostDTO>> sendPost( @Body NewPost post );
}





