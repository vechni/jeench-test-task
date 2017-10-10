package com.testtask.semyonov.jeench.module.act_users;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.data.exeptions.NoConnectivityException;
import com.testtask.semyonov.jeench.data.model.user.Address;
import com.testtask.semyonov.jeench.data.model.user.UserDTO;
import com.testtask.semyonov.jeench.module.act_users.UsersContract.View;
import com.testtask.semyonov.jeench.module.act_users.item.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UsersPresenter
        extends BasePresenter<View>
        implements UsersContract.Presenter
{
    public static final String TAG = UsersPresenter.class.getSimpleName();

    private View view;
    @Inject DataLayer dataLayer;

    UsersPresenter(){
        getPresenterComponent().inject(this);
    }

    @Override
    public void attachView( @NonNull final View view ){
        super.attachView(view);
        this.view = view;
    }

    @Override
    public void onStart(){
        super.onStart();
        unsubscribeOnDestroy(requestUsers());
    }

    @Override
    public void onRefresh(){
        unsubscribeOnDestroy(requestUsers());
    }

    @Override
    public void onClickItemUser( @NonNull final UserViewModel model ){
        view.navigateToUserScreen(model);
    }

    @NonNull
    private Disposable requestUsers(){
        return dataLayer.rest.requestUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    view.updateUsers(mapToViewModel(response));
                }, e->{
                    if( e instanceof NoConnectivityException ){
                        view.hideLoadIndicator();
                        view.showToastShort(e.getMessage());
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                });
    }

    @NonNull
    private List<UserViewModel> mapToViewModel( @NonNull final List<UserDTO> users ){
        final List<UserViewModel> list = new ArrayList<>();
        for( final UserDTO item : users ){
            list.add(new UserViewModel(item.getId(),
                                       item.getName(),
                                       item.getEmail(),
                                       formatAddress(item.getAddress())));
        }
        return list;
    }

    @NonNull
    private String formatAddress( @NonNull final Address address ){
        return address.getCity() + ", " + address.getStreet();
    }
}
