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

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: ViewModel is working...");
    }

    // Initiate call to the API. The source is the API call from the Flowable object.
    // Here we are converting the Flowable to the LiveData object.
    public void authenticateWithId(int userId) {

        // Set the Loading Status
        authUser.setValue(AuthResource.loading((User)null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams.fromPublisher(
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

        // Setting source (above) to authUser (below) using MediatorLiveData
        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                // Update any observes using setValue()
                authUser.setValue(user);

                // Remove source since we no longer need to listen to it.
                authUser.removeSource(source);
            }
        });
    }

    // LiveData object of required data type (User) that we are returning to the UI
    public LiveData<AuthResource<User>> observeUser() {
        return authUser;
    }

}
