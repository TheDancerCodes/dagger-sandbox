package com.thedancercodes.daggersandbox.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: ViewModel is working...");
    }

    // Initiate call to the API. The source is the API call from the Flowable object.
    // Here we are converting the Flowable to the LiveData object.
    public void authenticateWithId(int userId) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .subscribeOn(Schedulers.io()));

        // Setting source (above) to authUser (below) using MediatorLiveData
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                // Update any observes using setValue()
                authUser.setValue(user);

                // Remove source since we no longer need to listen to it.
                authUser.removeSource(source);
            }
        });
    }

    // LiveData object of required data type (User) that we are returning to the UI
    public LiveData<User> observeUser() {
        return authUser;
    }

}
