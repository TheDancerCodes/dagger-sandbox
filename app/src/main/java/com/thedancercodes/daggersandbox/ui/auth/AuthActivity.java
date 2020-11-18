package com.thedancercodes.daggersandbox.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.thedancercodes.daggersandbox.R;
import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    private EditText userId;

    // Used to instantiate the ViewModel
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager; // The Glide Instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Instantiate EditText
        userId = findViewById(R.id.user_id_input);

        // Attach OnClickListener to the Login button
        findViewById(R.id.login_button).setOnClickListener(this);

        // Instantiate the ViewModel
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();
    }

    // This method helps us observe the MediatorLiveData object (authUser) in AuthViewModel
    // from the UI.
    // Any changes made to the authUser LiveData object will be updated to the UI.
    private void subscribeObservers() {
       viewModel.observeUser().observe(this, new Observer<User>() {
           @Override
           public void onChanged(User user) {

               // Ensure User isn't null & is successfully authenticated.
               if (user != null) {
                   Log.d(TAG, "onChanged: " + user.getEmail());
               }
           }
       });
    }

    // Method to set a logo
    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_button: {
                attemptLogin();
                break;
            }
        }
    }

    private void attemptLogin() {

        // Check for null user id
        if (TextUtils.isEmpty(userId.getText().toString())) {
            return;
        }

        // Attempt login when user id is not null
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));

    }
}