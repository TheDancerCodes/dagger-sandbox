package com.thedancercodes.daggersandbox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.ui.auth.AuthActivity;
import com.thedancercodes.daggersandbox.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Abstract because we will be extending our other activities by this class.
 */
public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    // Inject SessionManager to keep track of user's authentication state in every
    // Activity/ Fragment of the Application.
    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    // Observe Authentication State
    private void subscribeObservers() {
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                // Ensure userAuthResource isn't null
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {

                        // Check what the status is and switch it
                        case LOADING: {
                            break;
                        }

                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: "
                                    + userAuthResource.data.getEmail());
                            break;
                        }

                        case ERROR: {
                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            navLoginScreen();
                            break;
                        }
                    }
                }
            }
        });
    }

    // Redirect user to login screen if they are not authenticated.
    private void navLoginScreen() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
