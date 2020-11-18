package com.thedancercodes.daggersandbox.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: ViewModel is working...");

        // Check that the AuthApi is being injected as a dependency.
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL.");
        } else {
            Log.d(TAG, "AuthViewModel: auth api is NOT NULL.");
        }
    }
}
