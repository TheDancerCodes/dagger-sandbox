package com.thedancercodes.daggersandbox.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: ViewModel is working...");

        // Make request and run it on a background thread
        authApi.getUser(1)
                .toObservable() // Convert Flowable object to an Observable
                .subscribeOn(Schedulers.io()) // Subscribe on a background thread
                .subscribe(new Observer<User>() { // New observer to observe the Observable
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
