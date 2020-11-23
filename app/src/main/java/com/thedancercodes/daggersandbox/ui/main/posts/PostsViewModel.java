package com.thedancercodes.daggersandbox.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.thedancercodes.daggersandbox.SessionManager;
import com.thedancercodes.daggersandbox.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    // Dependencies we are injecting into the ViewModel
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: ViewModel is working...");
    }
}
