package com.testtask.semyonov.jeench.data.rest.retrofit;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.data.model.album.AlbumDTO;
import com.testtask.semyonov.jeench.data.model.album_detail.AlbumDetailDTO;
import com.testtask.semyonov.jeench.data.model.post.NewPost;
import com.testtask.semyonov.jeench.data.model.post.PostDTO;
import com.testtask.semyonov.jeench.data.model.user.UserDTO;
import com.testtask.semyonov.jeench.data.rest.RestClient;
import com.testtask.semyonov.jeench.utils.NetworkUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import retrofit2.Response;

public class RestClientImpl
        implements RestClient
{
    private RestApi restApi;

    public RestClientImpl( @NonNull final RestApi restApi ){
        this.restApi = restApi;
    }

    @Override
    public Observable<List<UserDTO>> requestUsers(){
        return restApi.requestUsers()
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<PostDTO>> requestPosts( final int userId ){
        return restApi.requestPosts(userId)
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<AlbumDTO>> requestAlbums( final int userId ){
        return restApi.requestAlbums(userId)
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<List<AlbumDetailDTO>> requestAlbum( final int albumId ){
        return restApi.requestAlbum(albumId)
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    @Override
    public Observable<PostDTO> sendPost( final int userId, @NonNull final String title, @NonNull final String body ){
        NewPost post = new NewPost(userId, title, body);
        return restApi.sendPost(post)
                .map(response->{
                    checkResponse(response);
                    return response.body();
                })
                .timeout(NetworkUtils.WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    private void checkResponse( @NonNull final Response response ) throws RuntimeException{
        if( ! response.isSuccessful() ){
            throw new RuntimeException(String.valueOf(response.code()));
        }
    }
}
