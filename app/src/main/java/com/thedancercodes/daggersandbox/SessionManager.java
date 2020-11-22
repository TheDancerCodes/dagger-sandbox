package com.thedancercodes.daggersandbox;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class manages the authenticated user.
 *
 * It spans the entire lifetime of the application and exists in the AppComponent,
 * so it can be injected anywhere as a dependency in the application.
 */
@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    // Use MediatorLiveData so that we can observe the authenticated user from any class
    // that we inject the SessionManager into. Holding an Observable object instead of a raw object.
    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    // Annotate the constructor with @Inject because we will inject the SessionManager in various
    // places in the application.
    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {

        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading((User)null));

            // Add a new source
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }
    }

    public void logOut() {
        Log.d(TAG, "logOut: logging out...");

        // Remove cachedUser object from MediatorLiveData
        cachedUser.setValue(AuthResource.<User>logout());
    }

    // LiveData object for observing
    public LiveData<AuthResource<User>> getAuthUser() {
        return cachedUser;
    }
}
