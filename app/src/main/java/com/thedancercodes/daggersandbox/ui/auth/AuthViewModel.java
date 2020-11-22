package com.thedancercodes.daggersandbox.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.SessionManager;
import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    // Inject
    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: ViewModel is working...");
    }

    public void authenticateWithId(int userId) {

        Log.d(TAG, "authenticateWithId: attempting to login.");

        sessionManager.authenticateWithId(queryUserId(userId));
    }

    // Initiate call to the API. The source is the API call from the Flowable object.
    // Here we are converting the Flowable to the LiveData object.
    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)

                        // Called if error occurs (instead of calling onError)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @NonNull
                            @Override
                            public User apply(@NonNull Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })

                        // This operator receives the errorUser object if there was an error OR
                        // receive the User object from the API if there was no error.
                        .map(new Function<User, AuthResource<User>>() {
                            @NonNull
                            @Override
                            public AuthResource<User> apply(@NonNull User user) throws Exception {

                                // Return message if there's an error
                                if (user.getId() == -1){
                                    return AuthResource.error("Could not authenticate", (User)null);
                                }

                                // Authenticate if there no error
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    // LiveData object of required data type (User) that we are returning to the UI
    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }

}
