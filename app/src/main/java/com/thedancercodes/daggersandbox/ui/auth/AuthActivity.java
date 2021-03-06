package com.thedancercodes.daggersandbox.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.thedancercodes.daggersandbox.R;
import com.thedancercodes.daggersandbox.models.User;
import com.thedancercodes.daggersandbox.ui.main.MainActivity;
import com.thedancercodes.daggersandbox.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";

    private AuthViewModel viewModel;

    private EditText userId;

    private ProgressBar progressBar;

    // Used to instantiate the ViewModel
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager; // The Glide Instance

    @Inject
    @Named("app_user")
    User userNumber1;

    @Inject
    @Named("auth_user")
    User userNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // Instantiate EditText
        userId = findViewById(R.id.user_id_input);

        // Instantiate ProgressBar
        progressBar = findViewById(R.id.progress_bar);

        // Attach OnClickListener to the Login button
        findViewById(R.id.login_button).setOnClickListener(this);

        // Instantiate the ViewModel
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);

        setLogo();

        subscribeObservers();

        Log.d(TAG, "onCreate: app_user ~> " + userNumber1); // From @Singleton scope
        Log.d(TAG, "onCreate: auth_user ~> " + userNumber2); // From @AuthScope scope
    }

    // This method helps us observe the MediatorLiveData object (authUser) in AuthViewModel
    // from the UI.
    // Any changes made to the authUser LiveData object will be updated to the UI.
    private void subscribeObservers() {
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {

            // When we set a value in the AuthViewModel, the OnChanged method below will trigger
            // if the status changes and we can update the UI.
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                // Ensure userAuthResource isn't null
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {

                        // Check what the status is and switch it
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }

                        case AUTHENTICATED: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: "
                                    + userAuthResource.data.getEmail());
                            onLoginSuccess();
                            break;
                        }

                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message
                                    + "\n Did you enter a number between 1 and 10?",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }

                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });
    }

    // Method to show/ hide the ProgressBar
    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    // Method to redirect to MainActivity when a user is authenticated
    private void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
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